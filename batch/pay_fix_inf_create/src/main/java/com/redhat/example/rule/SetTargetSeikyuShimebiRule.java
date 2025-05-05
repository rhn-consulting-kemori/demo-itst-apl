package com.redhat.example.rule;

// Spring
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// Config
import com.redhat.example.config.AppConfig;
import com.redhat.example.util.GetSimurationDate;

@Component
public class SetTargetSeikyuShimebiRule {

    @Autowired
    private AppConfig appConfig;

    public String getTargetShimebi() {

        if(appConfig.getSeikyushimebi() != null && appConfig.getSeikyushimebi().length() == 8) {
            return appConfig.getSeikyushimebi();
        } else {
            // 想定日設定
            GetSimurationDate simu_date_obj = new GetSimurationDate(appConfig.getSimulationdate());
            return simu_date_obj.getSimulation_date().substring(0,6) + "15";
        }
        
    }
}
