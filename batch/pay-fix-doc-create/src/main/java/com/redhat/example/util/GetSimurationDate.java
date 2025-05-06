package com.redhat.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;
import lombok.Data;

@Data
public class GetSimurationDate {

    /** Config Parameter */
    private String simulation_date;

    public GetSimurationDate(String env_simu_date) {
        if(StringUtils.isEmpty(env_simu_date)) {
            LocalDateTime nowDate = LocalDateTime.now();
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyyMMdd");
            setSimulation_date(dtf1.format(nowDate));
        } else {
            setSimulation_date(env_simu_date);
        }
        System.out.println("ENV_DATE: " + env_simu_date + " / SIMU_DATE: " + simulation_date);
    }

}
