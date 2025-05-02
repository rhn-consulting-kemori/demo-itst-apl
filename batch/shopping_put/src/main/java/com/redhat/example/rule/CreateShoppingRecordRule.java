package com.redhat.example.rule;

import java.util.List;
import java.util.Map;

// Spring
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// Business Object
import com.redhat.example.entity.ShoppingRecordEntity;

// Rule
import com.redhat.example.util.CalcurateSeikyuShimebi;
import com.redhat.example.util.GetSimurationDate;

// Config
import com.redhat.example.config.AppConfig;

@Component
public class CreateShoppingRecordRule {

    @Autowired
    private AppConfig appConfig;

    public ShoppingRecordEntity executeRule(Map<String, Object> exchange_message) {

        // 想定日設定
        GetSimurationDate simu_date_obj = new GetSimurationDate(appConfig.getSimulationdate());

        // ShoppingRecordEntity
        ShoppingRecordEntity entity = new ShoppingRecordEntity();

        Map<String, Object> shopping_entry = exchange_message;
        entity.setShopping_number(Integer.valueOf(String.valueOf(shopping_entry.get("shopping_number"))));
        entity.setCustomer_number(shopping_entry.get("customer_number").toString());
        entity.setUse_card_number(shopping_entry.get("use_card_number").toString());
        entity.setSeikyu_shimebi(
            new CalcurateSeikyuShimebi().getSeikyuShimebi(
                shopping_entry.get("use_date").toString(), simu_date_obj.getSimulation_date())
        );
        entity.setPay_type(shopping_entry.get("pay_type").toString());
        entity.setUse_date(shopping_entry.get("use_date").toString());
        entity.setShop_name(shopping_entry.get("shop_name").toString());
        entity.setUse_amount(Integer.valueOf(String.valueOf(shopping_entry.get("use_amount"))));

        return entity;
    }
    
}
