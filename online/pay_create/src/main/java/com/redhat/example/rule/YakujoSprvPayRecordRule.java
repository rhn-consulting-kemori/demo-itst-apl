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
import com.redhat.example.type.SprvYakujohenkoPayRecordTypeRequest;

// Buisiness Rule
import com.redhat.example.rule.SprvCalcurateRule;

@Component
public class YakujoSprvPayRecordRule {
    @Autowired
    private AppConfig appConfig;

    // 想定日
    private GetSimurationDate simuration_date_obj;

    public PayRecordEntity yakujoSprvPayRecord(SprvYakujohenkoPayRecordTypeRequest request) {
        // 想定日設定
        simuration_date_obj = new GetSimurationDate(appConfig.getSimulationdate());
        
        if(request.getPayrecord() == null) {
            return null;
        } else {
            return updateSprvrecord(request);
        }
    }

    public PayRecordEntity updateSprvrecord(SprvYakujohenkoPayRecordTypeRequest request) {
        PayRecordEntity entity = request.getPayrecord();
        entity.setSprv_init_furi_menjo(request.getCustomer().getSprv_init_furi_menjo());
        entity.setSprv_monthly_payamount(request.getCustomer().getSprv_monthly_payamount());
        entity.setSprv_rate(request.getCustomer().getSprv_rate());

        SprvCalcurateRule sprv_cal_rule = new SprvCalcurateRule();
        return sprv_cal_rule.getYakujoHennkoPayRecord(entity);

    }
}
