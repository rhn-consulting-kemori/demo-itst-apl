package com.redhat.example.rule;

// Spring
import org.springframework.stereotype.Component;

// Config / Util
import com.redhat.example.util.CalcurateDateUtil;

// Business Object
import com.redhat.example.type.PayCreateTypeRequest;
import com.redhat.example.type.PreShimeDataTypeRequest;

@Component
public class GetPreShimeDataRule {
    public PreShimeDataTypeRequest getPreShimeDataTypeRequest(PayCreateTypeRequest request) {
        PreShimeDataTypeRequest obj = new PreShimeDataTypeRequest();
        obj.setCustomer_number(request.getCustomer_number());
        CalcurateDateUtil dateutil = new CalcurateDateUtil();
        obj.setSeikyu_shimebi(dateutil.string_convert(dateutil.getMonthAddDate(dateutil.date_convert(request.getSeikyu_shimebi()), -1)));
        return obj;
    }
}
