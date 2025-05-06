package com.redhat.example.processor;

// Camel
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// Spring
import org.springframework.stereotype.Component;

// Business Object
import com.redhat.example.type.YakujoHenkoTypeRequest;
import com.redhat.example.entity.CustomerEntity;
import com.redhat.example.type.SetYakujoImageTypeRequest;

@Component
public class SetYakujoImageProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        /**
         * Exchange Property
         */
        SetYakujoImageTypeRequest obj = new SetYakujoImageTypeRequest();
        obj.setRequest(exchange.getProperty("request", YakujoHenkoTypeRequest.class));
        obj.setCustomer(exchange.getProperty("customer", CustomerEntity.class));
        
        /**
         * Exchange OUT
         */
        exchange.getMessage().setBody(obj);
    }
}
