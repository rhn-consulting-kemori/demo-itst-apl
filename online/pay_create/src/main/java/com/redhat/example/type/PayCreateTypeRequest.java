package com.redhat.example.type;

import lombok.Data;

@Data
public class PayCreateTypeRequest {
    /** 顧客番号 */
    private String customer_number;

    /** 請求締日 */
    private String seikyu_shimebi;

    /** 支払方法 */
    private String pay_type;

    /** 利用日 */
    private String use_date;

    /** 利用金額 */
    private int use_amount;
    
}
