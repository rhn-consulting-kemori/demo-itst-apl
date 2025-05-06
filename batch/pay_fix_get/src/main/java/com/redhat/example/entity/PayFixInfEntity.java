package com.redhat.example.entity;

import lombok.Data;

@Data
public class PayFixInfEntity {

    /** 顧客番号 */ 
    private String customer_number;

    /** 請求締日 */ 
    private String seikyu_shimebi;

    /** 決済日 */
    private String pay_date;

    /** 請求額 */
    private int pay_amount;

    /** SP1回請求額 */
    private int sp1_pay_amount;

    /** SPリボ請求元金 */
    private int sprv_pay_gankin;

    /** SPリボ請求手数料 */
    private int sprv_pay_tesuryo;

    /** カード番号 */
    private String card_number;

    /** 氏名 */
    private String customer_name;

    /** 住所 */
    private String customer_address;

    /** SPリボ利用限度額 */
    private int sprv_limit_amount;

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
    
}
