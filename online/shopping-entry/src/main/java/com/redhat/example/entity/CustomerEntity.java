package com.redhat.example.entity;

import lombok.Data;

@Data
public class CustomerEntity {

    /** 顧客番号 */
    private String customer_number;

    /** カード番号 */
    private String card_number;

    /** 氏名 */
    private String customer_name;

    /** 住所 */
    private String customer_address;

    /** SPリボ利用限度額 */
    private int sprv_limit_amount;

    /** SPリボ初回付利免除 */
    private String sprv_init_furi_menjo;

    /** SPリボ毎月返済額 */
    private int sprv_monthly_payamount;

    /** SPリボ手数料率 */
    private double sprv_rate;
    
}
