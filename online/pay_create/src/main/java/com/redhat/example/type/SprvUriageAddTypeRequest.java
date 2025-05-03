package com.redhat.example.type;

import lombok.Data;

@Data
public class SprvUriageAddTypeRequest {
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

    /** SPリボ請求元金 */
    private int sprv_pay_gankin;

    /** SPリボ請求手数料 */
    private int sprv_pay_tesuryo;

    /** SPリボ当月請求元金 */
    private int sprv_togetu_zandaka;

    /** SPリボ当月繰越残高 */
    private int sprv_togetu_kurizan;

    /** SPリボ次月繰越残高 */
    private int sprv_jigetu_kurizan;

    /** SPリボ初回付利免除 */
    private String sprv_init_furi_menjo;

    /** SPリボ毎月返済額 */
    private int sprv_monthly_payamount;

    /** SPリボ手数料率 */
    private double sprv_rate;

    /** 利用日 */
    private String use_date;

    /** 利用金額 */
    private int use_amount;
    
}
