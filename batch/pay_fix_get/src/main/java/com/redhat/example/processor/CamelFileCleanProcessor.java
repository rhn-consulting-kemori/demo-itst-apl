package com.redhat.example.processor;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

// Config
import com.redhat.example.config.AppConfig;

@Component
public class CamelFileCleanProcessor implements Processor {
    @Autowired
    private AppConfig appConfig;

    @Override
    public void process(Exchange exchange) throws Exception {
        if(appConfig.getDeletefilepath() != null && appConfig.getDeletefilepath().length() > 0){
            try {
                Path p = Paths.get(appConfig.getDeletefilepath());
                Files.delete(p);
                System.out.println("Process: Delete File Success -> " + appConfig.getDeletefilepath());
            } catch (IOException e) {
                System.out.println("Process: Delete File Failed");
                System.out.println(e);
            }
        } else {
            System.out.println("Process: Delete File Nothing");
        }
    }
}
