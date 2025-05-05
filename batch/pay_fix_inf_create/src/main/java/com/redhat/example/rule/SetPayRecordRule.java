package com.redhat.example.rule;

import java.util.List;
import java.util.Map;

// Spring
import org.springframework.stereotype.Component;

// Business Object
import com.redhat.example.entity.PayRecordEntity;

@Component
public class SetPayRecordRule {
    public PayRecordEntity executeRule(Map<String, Object> exchange_message) {
        PayRecordEntity entity = new PayRecordEntity();
        entity.setCustomer_number(exchange_message.get("customer_number").toString());
        entity.setSeikyu_shimebi(exchange_message.get("seikyu_shimebi").toString());
        entity.setPay_date(exchange_message.get("pay_date").toString());
        entity.setPay_amount(Integer.valueOf(String.valueOf(exchange_message.get("pay_amount"))));
        entity.setSp1_pay_amount(Integer.valueOf(String.valueOf(exchange_message.get("sp1_pay_amount"))));
        entity.setSprv_pay_gankin(Integer.valueOf(String.valueOf(exchange_message.get("sprv_pay_gankin"))));
        entity.setSprv_pay_tesuryo(Integer.valueOf(String.valueOf(exchange_message.get("sprv_pay_tesuryo"))));
        entity.setSprv_togetu_zandaka(Integer.valueOf(String.valueOf(exchange_message.get("sprv_togetu_zandaka"))));
        entity.setSprv_togetu_kurizan(Integer.valueOf(String.valueOf(exchange_message.get("sprv_togetu_kurizan"))));
        entity.setSprv_jigetu_kurizan(Integer.valueOf(String.valueOf(exchange_message.get("sprv_jigetu_kurizan"))));
        entity.setSprv_init_furi_menjo(exchange_message.get("sprv_init_furi_menjo").toString());
        entity.setSprv_monthly_payamount(Integer.valueOf(String.valueOf(exchange_message.get("sprv_monthly_payamount"))));
        entity.setSprv_rate(Double.valueOf(String.valueOf(exchange_message.get("sprv_rate"))));
        return entity;
    }
}
