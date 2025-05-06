package com.redhat.example.rule;

import java.util.List;
import java.util.Map;

// Spring
import org.springframework.stereotype.Component;

// Business Object
import com.redhat.example.entity.ShoppingRecordEntity;

@Component
public class SetShoppinRecordRule {
    public ShoppingRecordEntity executeRule(Map<String, Object> exchange_message) {
        ShoppingRecordEntity entity = new ShoppingRecordEntity();
        entity.setShopping_number(Integer.valueOf(String.valueOf(exchange_message.get("shopping_number"))));
        entity.setCustomer_number(exchange_message.get("customer_number").toString());
        entity.setUse_card_number(exchange_message.get("use_card_number").toString());
        entity.setSeikyu_shimebi(exchange_message.get("seikyu_shimebi").toString());
        entity.setPay_type(exchange_message.get("pay_type").toString());
        entity.setUse_date(exchange_message.get("use_date").toString());
        entity.setShop_name(exchange_message.get("shop_name").toString());
        entity.setUse_amount((Integer.valueOf(String.valueOf(exchange_message.get("use_amount")))));
        return entity;
    }
}
