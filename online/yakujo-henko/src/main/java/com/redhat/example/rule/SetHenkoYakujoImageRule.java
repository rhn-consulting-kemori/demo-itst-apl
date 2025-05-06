package com.redhat.example.rule;

// Spring
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// config
import com.redhat.example.config.AppConfig;

// BUsiness Object
import com.redhat.example.type.SetYakujoImageTypeRequest;
import com.redhat.example.entity.YakujoHenkoHistoryEntity;

// Util
import com.redhat.example.util.GetSimurationDate;
import com.redhat.example.util.CalcurateSeikyuShimebi;

@Component
public class SetHenkoYakujoImageRule {
    
    @Autowired
    private AppConfig appConfig;

    public YakujoHenkoHistoryEntity getHenkoYakujoImage(SetYakujoImageTypeRequest request) {
        YakujoHenkoHistoryEntity entity = new YakujoHenkoHistoryEntity();
        entity.setCustomer_number(request.getRequest().getCustomer_number());
        entity.setBefore_sprv_limit_amount(request.getCustomer().getSprv_limit_amount());
        entity.setBefore_sprv_init_furi_menjo(request.getCustomer().getSprv_init_furi_menjo());
        entity.setBefore_sprv_monthly_payamount(request.getCustomer().getSprv_monthly_payamount());
        entity.setBefore_sprv_rate(request.getCustomer().getSprv_rate());

        // 変更項目設定
        if(request.getRequest().getSprv_limit_amount_flg().equals("1")) {
            entity.setAfter_sprv_limit_amount(request.getRequest().getSprv_limit_amount());
        } else {
            entity.setAfter_sprv_limit_amount(entity.getBefore_sprv_limit_amount());
        }

        if(request.getRequest().getSprv_init_furi_menjo_flg().equals("1")) {
            entity.setAfter_sprv_init_furi_menjo(request.getRequest().getSprv_init_furi_menjo());
        } else {
            entity.setAfter_sprv_init_furi_menjo(entity.getBefore_sprv_init_furi_menjo());
        }

        if(request.getRequest().getSprv_monthly_payamount_flg().equals("1")) {
            entity.setAfter_sprv_monthly_payamount(request.getRequest().getSprv_monthly_payamount());
        } else {
            entity.setAfter_sprv_monthly_payamount(entity.getBefore_sprv_monthly_payamount());
        }

        if(request.getRequest().getSprv_rate_flg().equals("1")) {
            entity.setAfter_sprv_rate(request.getRequest().getSprv_rate());
        } else {
            entity.setAfter_sprv_rate(entity.getBefore_sprv_rate());
        }

        // 日付情報
        // 想定日設定
        GetSimurationDate simu_date_obj = new GetSimurationDate(appConfig.getSimulationdate());
        entity.setChange_date(simu_date_obj.getSimulation_date());

        CalcurateSeikyuShimebi cal_date_obj = new CalcurateSeikyuShimebi();
        entity.setPay_change_seikyu_shimebi(cal_date_obj.getYakujoHenkoSeikyuShimebi(simu_date_obj.getSimulation_date()));

        return entity;
    }
}
