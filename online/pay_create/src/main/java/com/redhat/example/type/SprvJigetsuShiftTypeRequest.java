package com.redhat.example.type;

import lombok.Data;

@Data
public class SprvJigetsuShiftTypeRequest {

    /** 顧客番号 */ 
    private String customer_number;

    /** 請求締日 */ 
    private String seikyu_shimebi;

    /** 決済日 */
    private String pay_date;

    /** 前月請求締日 */
    private String zengetsu_seikyu_shimebi;

    /** 前月決済日 */
    private String zengetsu_pay_date;

    /** 前月繰越残高 */
    private int sprv_zengetsu_kurizan;

    /** SPリボ毎月返済額 */
    private int sprv_monthly_payamount;

    /** SPリボ手数料率 */
    private double sprv_rate;
}
