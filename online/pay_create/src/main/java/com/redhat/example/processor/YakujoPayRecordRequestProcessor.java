package com.redhat.example.processor;

// Camel
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// Spring
import org.springframework.stereotype.Component;

// Business Object
import com.redhat.example.type.SprvYakujoHenkoTypeRequest;
import com.redhat.example.entity.CustomerEntity;
import com.redhat.example.entity.PayRecordEntity;
import com.redhat.example.type.SprvYakujohenkoPayRecordTypeRequest;

@Component
public class YakujoPayRecordRequestProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        /**
         * Exchange Property
         */
        SprvYakujohenkoPayRecordTypeRequest obj = new SprvYakujohenkoPayRecordTypeRequest();
        obj.setRequest(exchange.getProperty("request_yakujo", SprvYakujoHenkoTypeRequest.class));
        obj.setCustomer(exchange.getProperty("customer", CustomerEntity.class));
        obj.setPayrecord(exchange.getProperty("pay_record_togetsu", PayRecordEntity.class));
        
        /**
         * Exchange OUT
         */
        exchange.getMessage().setBody(obj);
    }
}
