package com.redhat.example.rule;

import java.util.ArrayList;
import java.util.List;

// Spring
import org.springframework.stereotype.Component;

// Config / Util
import com.redhat.example.util.CalcurateDateUtil;

// Business Object
import com.redhat.example.entity.PayRecordEntity;
import com.redhat.example.type.SprvJigetsuShiftTypeRequest;
import com.redhat.example.type.SprvJigetsuShiftTypeResponse;
import com.redhat.example.type.SprvUriageAddTypeRequest;
import com.redhat.example.type.SprvUriageAddTypeResponse;

@Component
public class SprvCalcurateRule {
    public SprvJigetsuShiftTypeResponse getSprvJigetsuShiftPayRecord(SprvJigetsuShiftTypeRequest request) {
        SprvJigetsuShiftTypeResponse response = new SprvJigetsuShiftTypeResponse();
        response.setCustomer_number(request.getCustomer_number());
        response.setSeikyu_shimebi(request.getSeikyu_shimebi());

        if(request.getSprv_zengetsu_kurizan() == 0) {
            // 繰越残高が0なので、翌月請求なし
        } else if(request.getSprv_zengetsu_kurizan() > request.getSprv_monthly_payamount()) {
            response.setSprv_pay_gankin(request.getSprv_monthly_payamount());
            response.setSprv_togetu_zandaka(0);
            response.setSprv_togetu_kurizan(request.getSprv_zengetsu_kurizan());
            response.setSprv_jigetu_kurizan(request.getSprv_zengetsu_kurizan() - request.getSprv_monthly_payamount());
            response.setSprv_pay_tesuryo(
                calTesuryo(
                    request.getSprv_zengetsu_kurizan(), 
                    calFurikaishi(request.getZengetsu_pay_date()), 
                    request.getPay_date(), 
                    request.getSprv_rate()
                )
            );
        } else {
            response.setSprv_pay_gankin(request.getSprv_zengetsu_kurizan());
            response.setSprv_togetu_zandaka(0);
            response.setSprv_togetu_kurizan(request.getSprv_zengetsu_kurizan());
            response.setSprv_jigetu_kurizan(0);
            response.setSprv_pay_tesuryo(
                calTesuryo(
                    request.getSprv_zengetsu_kurizan(), 
                    calFurikaishi(request.getZengetsu_pay_date()), 
                    request.getPay_date(), 
                    request.getSprv_rate()
                )
            );
        }

        return response;
    }

    public SprvUriageAddTypeResponse getUriageAddPayRecord(SprvUriageAddTypeRequest request) {
        SprvUriageAddTypeResponse response = new SprvUriageAddTypeResponse();
        response.setCustomer_number(request.getCustomer_number());
        response.setSeikyu_shimebi(request.getSeikyu_shimebi());

        // 付利対象残高
        response.setSprv_togetu_zandaka(request.getSprv_togetu_zandaka() + request.getUse_amount());
        response.setSprv_togetu_kurizan(request.getSprv_togetu_kurizan());

        // 請求割り当て
        int[] alloc_zandaka = allocateGanpon(response.getSprv_togetu_zandaka(), response.getSprv_togetu_kurizan(), request.getSprv_monthly_payamount());
        response.setSprv_jigetu_kurizan(
            response.getSprv_togetu_zandaka() + response.getSprv_togetu_kurizan() - alloc_zandaka[1] - alloc_zandaka[2]
        );
        response.setSprv_pay_gankin(alloc_zandaka[1] + alloc_zandaka[2]);

        // 利息算出
        int shinki_furi = judgeShinkiFuriZandaka(response.getSprv_togetu_zandaka(), response.getSprv_jigetu_kurizan(), request.getSprv_init_furi_menjo());
        response.setSprv_pay_tesuryo(
            calTesuryoShinkiAndKurizan(
                shinki_furi, calFurikaishi(request.getSeikyu_shimebi()), request.getPay_date(), 
                response.getSprv_togetu_kurizan(), calFurikaishi(request.getZengetsu_pay_date()), request.getPay_date(),
                request.getSprv_rate()
            )
        );
        
        return response;
        
    }

    public PayRecordEntity getYakujoHennkoPayRecord(PayRecordEntity entity) {
        PayRecordEntity response = entity;

        // 請求割り当て
        int[] alloc_zandaka = allocateGanpon(
            response.getSprv_togetu_zandaka(), response.getSprv_togetu_kurizan(), response.getSprv_monthly_payamount()
        );
        response.setSprv_jigetu_kurizan(
            response.getSprv_togetu_zandaka() + response.getSprv_togetu_kurizan() - alloc_zandaka[1] - alloc_zandaka[2]
        );
        response.setSprv_pay_gankin(alloc_zandaka[1] + alloc_zandaka[2]);

        // 利息算出
        int shinki_furi = judgeShinkiFuriZandaka(response.getSprv_togetu_zandaka(), response.getSprv_jigetu_kurizan(), response.getSprv_init_furi_menjo());
        CalcurateDateUtil dateUtil = new CalcurateDateUtil();
        String zengetsu_pay_date = dateUtil.string_convert(dateUtil.getMonthAddDate(dateUtil.date_convert(response.getPay_date()), -1));

        response.setSprv_pay_tesuryo(
            calTesuryoShinkiAndKurizan(
                shinki_furi, calFurikaishi(response.getSeikyu_shimebi()), response.getPay_date(), 
                response.getSprv_togetu_kurizan(), calFurikaishi(zengetsu_pay_date), response.getPay_date(),
                response.getSprv_rate()
            )
        );
        
        response.setPay_amount(response.getSp1_pay_amount() + response.getSprv_pay_gankin() + response.getSprv_pay_tesuryo());
        return response;
    }

    // 手数料算出
    public int calTesuryo(int gankin, String furi_start_date, String furi_end_date, double rate) {
        CalcurateDateUtil dateutil = new CalcurateDateUtil();
        long furi_kikan = dateutil.date_between(furi_start_date, furi_end_date) + 1;
        double tesuryo = (double)gankin * (double)furi_kikan * rate / 365;
        return (int)tesuryo;
    }

    // 手数料算出（新規＋繰越）
    public int calTesuryoShinkiAndKurizan(int shinki_gankin, String shinki_furi_start_date, String shinki_furi_end_date, int kurizan_gankin, String kurizan_furi_start_date, String kurizan_furi_end_date, double rate) {
        CalcurateDateUtil dateutil = new CalcurateDateUtil();
        long shinki_furi_kikan = dateutil.date_between(shinki_furi_start_date, shinki_furi_end_date) + 1;
        long kurizan_furi_kikan = dateutil.date_between(kurizan_furi_start_date, kurizan_furi_end_date) + 1;
        double tesuryo = ((double)shinki_gankin * (double)shinki_furi_kikan + (double)kurizan_gankin * (double)kurizan_furi_kikan) * rate / 365;
        return (int)tesuryo;
    }

    // 前月付利開始日算出
    public String calFurikaishi(String zengetsu_pay_date) {
        CalcurateDateUtil dateutil = new CalcurateDateUtil();
        return dateutil.string_convert(dateutil.getDayAddDate(dateutil.date_convert(zengetsu_pay_date), 1));
    }

    // 請求元本割り当て
    public int[] allocateGanpon(int shinki, int kurizan , int sprv_monthly_payamount) {
        int zan_ganpon = sprv_monthly_payamount;
        int allocate_shinki = 0;
        int allocate_kurizan = 0;

        if(zan_ganpon > kurizan) {
            allocate_kurizan = kurizan;
            zan_ganpon = zan_ganpon - kurizan;
            if(zan_ganpon > shinki) {
                allocate_shinki = shinki;
                zan_ganpon = zan_ganpon - shinki;
            } else {
                allocate_shinki = zan_ganpon;
                zan_ganpon = 0;
            }
        } else {
            allocate_kurizan = zan_ganpon;
            zan_ganpon = 0;
        }
        
        int[] result = new int[3];
        result[0] = zan_ganpon;
        result[1] = allocate_shinki;
        result[2] = allocate_kurizan;
        return result;
    }

    // 新規付利対象残高の判定
    public int judgeShinkiFuriZandaka(int shinki, int kurizan, String sprv_init_furi_menjo) {
        if(kurizan == 0 || sprv_init_furi_menjo.equals("1")) {
            return 0;
        } else {
            return shinki;
        }
    }
}
