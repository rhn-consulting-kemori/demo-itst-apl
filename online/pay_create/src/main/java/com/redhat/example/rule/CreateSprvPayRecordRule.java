package com.redhat.example.rule;

// Spring
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// Config / Util
import com.redhat.example.config.AppConfig;
import com.redhat.example.util.GetSimurationDate;
import com.redhat.example.util.CalcurateDateUtil;
import com.redhat.example.util.CalcurateSeikyuShimebi;

// BUsiness Object
import com.redhat.example.entity.PayRecordEntity;
import com.redhat.example.type.PayRecordCreateTypeRequest;
import com.redhat.example.type.SprvJigetsuShiftTypeRequest;
import com.redhat.example.type.SprvJigetsuShiftTypeResponse;
import com.redhat.example.type.SprvUriageAddTypeRequest;
import com.redhat.example.type.SprvUriageAddTypeResponse;

// Buisiness Rule
import com.redhat.example.rule.SprvCalcurateRule;

@Component
public class CreateSprvPayRecordRule {

    @Autowired
    private AppConfig appConfig;

    // 想定日
    private GetSimurationDate simuration_date_obj;

    public PayRecordEntity createSprvPayRecord(PayRecordCreateTypeRequest request) {
        // 想定日設定
        simuration_date_obj = new GetSimurationDate(appConfig.getSimulationdate());
        
        if(request.getPayrecord() == null) {
            return createSprvrecord(request);
        } else {
            return updateSprvrecord(request);
        }
    }

    public PayRecordEntity createSprvrecord(PayRecordCreateTypeRequest request) {
        PayRecordEntity entity = new PayRecordEntity();
        entity.setCustomer_number(request.getRequest().getCustomer_number());
        entity.setSeikyu_shimebi(request.getRequest().getSeikyu_shimebi());
        entity.setPay_date(new CalcurateSeikyuShimebi().getPaydate(request.getRequest().getSeikyu_shimebi()));
        entity.setSp1_pay_amount(0);
        entity.setSprv_init_furi_menjo(request.getCustomer().getSprv_init_furi_menjo());
        entity.setSprv_monthly_payamount(request.getCustomer().getSprv_monthly_payamount());
        entity.setSprv_rate(request.getCustomer().getSprv_rate());

        // 前月請求がある場合（リボ情報を設定する）
        if(request.getPayrecord_zengetsu() != null) {

            // 前月からの請求シフト
            // Request
            SprvJigetsuShiftTypeRequest sprv_jigetsu_request = new SprvJigetsuShiftTypeRequest();
            sprv_jigetsu_request.setCustomer_number(entity.getCustomer_number());
            sprv_jigetsu_request.setSeikyu_shimebi(entity.getSeikyu_shimebi());
            sprv_jigetsu_request.setPay_date(entity.getPay_date());
            sprv_jigetsu_request.setZengetsu_seikyu_shimebi(request.getPayrecord_zengetsu().getSeikyu_shimebi());
            sprv_jigetsu_request.setZengetsu_pay_date(request.getPayrecord_zengetsu().getPay_date());
            sprv_jigetsu_request.setSprv_zengetsu_kurizan(request.getPayrecord_zengetsu().getSprv_jigetu_kurizan());
            sprv_jigetsu_request.setSprv_monthly_payamount(entity.getSprv_monthly_payamount());
            sprv_jigetsu_request.setSprv_rate(entity.getSprv_rate());

            // Response
            SprvCalcurateRule sprv_cal_rule = new SprvCalcurateRule();
            SprvJigetsuShiftTypeResponse sprv_jigetsu_response = sprv_cal_rule.getSprvJigetsuShiftPayRecord(sprv_jigetsu_request);
            entity.setPay_amount(entity.getPay_amount() + sprv_jigetsu_response.getSprv_pay_gankin() + sprv_jigetsu_response.getSprv_pay_tesuryo());
            entity.setSprv_pay_gankin(sprv_jigetsu_response.getSprv_pay_gankin());
            entity.setSprv_pay_tesuryo(sprv_jigetsu_response.getSprv_pay_tesuryo());
            entity.setSprv_togetu_zandaka(sprv_jigetsu_response.getSprv_togetu_zandaka());
            entity.setSprv_togetu_kurizan(sprv_jigetsu_response.getSprv_togetu_kurizan());
            entity.setSprv_jigetu_kurizan(sprv_jigetsu_response.getSprv_jigetu_kurizan());

            // 利用分の加算
            // Request
            SprvUriageAddTypeRequest sprv_uriage_add_request = new SprvUriageAddTypeRequest();
            sprv_uriage_add_request.setCustomer_number(entity.getCustomer_number());
            sprv_uriage_add_request.setSeikyu_shimebi(entity.getSeikyu_shimebi());
            sprv_uriage_add_request.setPay_date(entity.getPay_date());
            sprv_uriage_add_request.setZengetsu_seikyu_shimebi(request.getPayrecord_zengetsu().getSeikyu_shimebi());
            sprv_uriage_add_request.setZengetsu_pay_date(request.getPayrecord_zengetsu().getPay_date());
            sprv_uriage_add_request.setSprv_pay_gankin(entity.getSprv_pay_gankin());
            sprv_uriage_add_request.setSprv_pay_tesuryo(entity.getSprv_pay_tesuryo());
            sprv_uriage_add_request.setSprv_togetu_zandaka(entity.getSprv_togetu_zandaka());
            sprv_uriage_add_request.setSprv_togetu_kurizan(entity.getSprv_togetu_kurizan());
            sprv_uriage_add_request.setSprv_jigetu_kurizan(entity.getSprv_jigetu_kurizan());
            sprv_uriage_add_request.setSprv_init_furi_menjo(entity.getSprv_init_furi_menjo());
            sprv_uriage_add_request.setSprv_monthly_payamount(entity.getSprv_monthly_payamount());
            sprv_uriage_add_request.setSprv_rate(entity.getSprv_rate());
            sprv_uriage_add_request.setUse_date(request.getRequest().getUse_date());
            sprv_uriage_add_request.setUse_amount(request.getRequest().getUse_amount());
    
            // Response
            SprvUriageAddTypeResponse sprv_uriage_add_response = sprv_cal_rule.getUriageAddPayRecord(sprv_uriage_add_request);
            entity.setPay_amount(entity.getSp1_pay_amount() + sprv_uriage_add_response.getSprv_pay_gankin() + sprv_uriage_add_response.getSprv_pay_tesuryo());
            entity.setSprv_pay_gankin(sprv_uriage_add_response.getSprv_pay_gankin());
            entity.setSprv_pay_tesuryo(sprv_uriage_add_response.getSprv_pay_tesuryo());
            entity.setSprv_togetu_zandaka(sprv_uriage_add_response.getSprv_togetu_zandaka());
            entity.setSprv_togetu_kurizan(sprv_uriage_add_response.getSprv_togetu_kurizan());
            entity.setSprv_jigetu_kurizan(sprv_uriage_add_response.getSprv_jigetu_kurizan());

        // 前月請求がない場合
        } else {
            // 利用分の加算
            // Request
            SprvUriageAddTypeRequest sprv_uriage_add_request = new SprvUriageAddTypeRequest();
            sprv_uriage_add_request.setCustomer_number(entity.getCustomer_number());
            sprv_uriage_add_request.setSeikyu_shimebi(entity.getSeikyu_shimebi());
            sprv_uriage_add_request.setPay_date(entity.getPay_date());

            CalcurateDateUtil dateutil = new CalcurateDateUtil();
            sprv_uriage_add_request.setZengetsu_seikyu_shimebi(
                dateutil.string_convert(dateutil.getMonthAddDate(dateutil.date_convert(entity.getSeikyu_shimebi()), -1))
            );
            sprv_uriage_add_request.setZengetsu_pay_date(
                dateutil.string_convert(dateutil.getMonthAddDate(dateutil.date_convert(entity.getPay_date()), -1))
            );

            sprv_uriage_add_request.setSprv_pay_gankin(0);
            sprv_uriage_add_request.setSprv_pay_tesuryo(0);
            sprv_uriage_add_request.setSprv_togetu_zandaka(0);
            sprv_uriage_add_request.setSprv_togetu_kurizan(0);
            sprv_uriage_add_request.setSprv_jigetu_kurizan(0);
            sprv_uriage_add_request.setSprv_init_furi_menjo(entity.getSprv_init_furi_menjo());
            sprv_uriage_add_request.setSprv_monthly_payamount(entity.getSprv_monthly_payamount());
            sprv_uriage_add_request.setSprv_rate(entity.getSprv_rate());
            sprv_uriage_add_request.setUse_date(request.getRequest().getUse_date());
            sprv_uriage_add_request.setUse_amount(request.getRequest().getUse_amount());
    
            // Response
            SprvCalcurateRule sprv_cal_rule = new SprvCalcurateRule();
            SprvUriageAddTypeResponse sprv_uriage_add_response = sprv_cal_rule.getUriageAddPayRecord(sprv_uriage_add_request);
            entity.setPay_amount(entity.getSp1_pay_amount() + sprv_uriage_add_response.getSprv_pay_gankin() + sprv_uriage_add_response.getSprv_pay_tesuryo());
            entity.setSprv_pay_gankin(sprv_uriage_add_response.getSprv_pay_gankin());
            entity.setSprv_pay_tesuryo(sprv_uriage_add_response.getSprv_pay_tesuryo());
            entity.setSprv_togetu_zandaka(sprv_uriage_add_response.getSprv_togetu_zandaka());
            entity.setSprv_togetu_kurizan(sprv_uriage_add_response.getSprv_togetu_kurizan());
            entity.setSprv_jigetu_kurizan(sprv_uriage_add_response.getSprv_jigetu_kurizan());
        }

        return entity;
    }

    public PayRecordEntity updateSprvrecord(PayRecordCreateTypeRequest request) {
        PayRecordEntity entity = request.getPayrecord();
        
        // Request
        SprvUriageAddTypeRequest sprv_uriage_add_request = new SprvUriageAddTypeRequest();
        sprv_uriage_add_request.setCustomer_number(entity.getCustomer_number());
        sprv_uriage_add_request.setSeikyu_shimebi(entity.getSeikyu_shimebi());
        sprv_uriage_add_request.setPay_date(entity.getPay_date());

        CalcurateDateUtil dateutil = new CalcurateDateUtil();
        sprv_uriage_add_request.setZengetsu_seikyu_shimebi(
            dateutil.string_convert(dateutil.getMonthAddDate(dateutil.date_convert(entity.getSeikyu_shimebi()), -1))
        );
        sprv_uriage_add_request.setZengetsu_pay_date(
            dateutil.string_convert(dateutil.getMonthAddDate(dateutil.date_convert(entity.getPay_date()), -1))
        );
        sprv_uriage_add_request.setSprv_pay_gankin(entity.getSprv_pay_gankin());
        sprv_uriage_add_request.setSprv_pay_tesuryo(entity.getSprv_pay_tesuryo());
        sprv_uriage_add_request.setSprv_togetu_zandaka(entity.getSprv_togetu_zandaka());
        sprv_uriage_add_request.setSprv_togetu_kurizan(entity.getSprv_togetu_kurizan());
        sprv_uriage_add_request.setSprv_jigetu_kurizan(entity.getSprv_jigetu_kurizan());
        sprv_uriage_add_request.setSprv_init_furi_menjo(entity.getSprv_init_furi_menjo());
        sprv_uriage_add_request.setSprv_monthly_payamount(entity.getSprv_monthly_payamount());
        sprv_uriage_add_request.setSprv_rate(entity.getSprv_rate());
        sprv_uriage_add_request.setUse_date(request.getRequest().getUse_date());
        sprv_uriage_add_request.setUse_amount(request.getRequest().getUse_amount());

        // Response
        SprvCalcurateRule sprv_cal_rule = new SprvCalcurateRule();
        SprvUriageAddTypeResponse sprv_uriage_add_response = sprv_cal_rule.getUriageAddPayRecord(sprv_uriage_add_request);
        entity.setPay_amount(entity.getSp1_pay_amount() + sprv_uriage_add_response.getSprv_pay_gankin() + sprv_uriage_add_response.getSprv_pay_tesuryo());
        entity.setSprv_pay_gankin(sprv_uriage_add_response.getSprv_pay_gankin());
        entity.setSprv_pay_tesuryo(sprv_uriage_add_response.getSprv_pay_tesuryo());
        entity.setSprv_togetu_zandaka(sprv_uriage_add_response.getSprv_togetu_zandaka());
        entity.setSprv_togetu_kurizan(sprv_uriage_add_response.getSprv_togetu_kurizan());
        entity.setSprv_jigetu_kurizan(sprv_uriage_add_response.getSprv_jigetu_kurizan());

        return entity;
    }
}
