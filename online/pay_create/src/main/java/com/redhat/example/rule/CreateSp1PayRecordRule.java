package com.redhat.example.rule;

// Spring
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// Config / Util
import com.redhat.example.config.AppConfig;
import com.redhat.example.util.GetSimurationDate;
import com.redhat.example.util.CalcurateSeikyuShimebi;

// BUsiness Object
import com.redhat.example.entity.PayRecordEntity;
import com.redhat.example.type.PayRecordCreateTypeRequest;
import com.redhat.example.type.SprvJigetsuShiftTypeRequest;
import com.redhat.example.type.SprvJigetsuShiftTypeResponse;

// Buisiness Rule
import com.redhat.example.rule.SprvCalcurateRule;

@Component
public class CreateSp1PayRecordRule {
    
    @Autowired
    private AppConfig appConfig;

    // 想定日
    private GetSimurationDate simuration_date_obj;

    public PayRecordEntity createSp1PayRecord(PayRecordCreateTypeRequest request) {
        // 想定日設定
        simuration_date_obj = new GetSimurationDate(appConfig.getSimulationdate());
        
        if(request.getPayrecord() == null) {
            return createSp1record(request);
        } else {
            return updateSp1record(request);
        }
    }

    public PayRecordEntity createSp1record(PayRecordCreateTypeRequest request) {
        PayRecordEntity entity = new PayRecordEntity();
        entity.setCustomer_number(request.getRequest().getCustomer_number());
        entity.setSeikyu_shimebi(request.getRequest().getSeikyu_shimebi());
        entity.setPay_date(new CalcurateSeikyuShimebi().getPaydate(request.getRequest().getSeikyu_shimebi()));
        entity.setPay_amount(request.getRequest().getUse_amount());
        entity.setSp1_pay_amount(request.getRequest().getUse_amount());
        entity.setSprv_init_furi_menjo(request.getCustomer().getSprv_init_furi_menjo());
        entity.setSprv_monthly_payamount(request.getCustomer().getSprv_monthly_payamount());
        entity.setSprv_rate(request.getCustomer().getSprv_rate());

        // 前月請求がある場合（リボ情報を設定する）
        if(request.getPayrecord_zengetsu() != null) {
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
        }
        return entity;
    }

    public PayRecordEntity updateSp1record(PayRecordCreateTypeRequest request) {
        PayRecordEntity entity = request.getPayrecord();
        entity.setPay_amount(entity.getPay_amount() + request.getRequest().getUse_amount());
        entity.setSp1_pay_amount(entity.getSp1_pay_amount() + request.getRequest().getUse_amount());
        return entity;
    }
}
