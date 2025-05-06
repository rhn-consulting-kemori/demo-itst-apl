package com.redhat.example.processor;

// Camel
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// Spring
import org.springframework.stereotype.Component;

// Business Object
import com.redhat.example.type.PayCreateTypeRequest;
import com.redhat.example.entity.CustomerEntity;
import com.redhat.example.entity.PayRecordEntity;
import com.redhat.example.type.PayRecordCreateTypeRequest;

@Component
public class CreatePayRecordRequestProcessor implements Processor {
    
    @Override
    public void process(Exchange exchange) throws Exception {
        /**
         * Exchange Property
         */
        PayRecordCreateTypeRequest obj = new PayRecordCreateTypeRequest();
        obj.setRequest(exchange.getProperty("request", PayCreateTypeRequest.class));
        obj.setCustomer(exchange.getProperty("customer", CustomerEntity.class));
        obj.setPayrecord(exchange.getProperty("pay_record_togetsu", PayRecordEntity.class));
        obj.setPayrecord_zengetsu(exchange.getProperty("pay_record_zengetsu", PayRecordEntity.class));
        
        /**
         * Exchange OUT
         */
        exchange.getMessage().setBody(obj);
    }
}
