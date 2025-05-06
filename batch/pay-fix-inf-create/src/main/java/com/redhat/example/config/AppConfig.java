package com.redhat.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("camel-app")
public class AppConfig {
    
    /** Config Parameter */
    private String simulationdate;
    private String seikyushimebi;
}
