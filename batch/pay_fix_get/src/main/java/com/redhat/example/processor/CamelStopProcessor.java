package com.redhat.example.processor;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Component
public class CamelStopProcessor implements Processor {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("Finishi Batch Process");
        exchange.getContext().getShutdownStrategy().setTimeout(5);
        SpringApplication.exit(applicationContext);
    }

}
