package com.redhat.example.type;

import lombok.Data;

@Data
public class PreShimeDataTypeRequest {
    
    /** 顧客番号 */
    private String customer_number;

    /** 請求締日 */
    private String seikyu_shimebi;

}
