package com.redhat.example.entity;

import lombok.Data;

@Data
public class YakujoHenkoHistoryEntity {
    /** 履歴番号 */
    private int history_seq;

    /** 顧客番号 */
    private String customer_number;

    /** 変更日 */
    private String change_date;

    /** 請求変更請求締日 */
    private String pay_change_seikyu_shimebi;

    /** 変更前SPリボ利用限度額 */
    private int before_sprv_limit_amount;

    /** 変更前SPリボ初回付利免除 */
    private String before_sprv_init_furi_menjo;

    /** 変更前SPリボ毎月返済額 */
    private int before_sprv_monthly_payamount;

    /** 変更前SPリボ手数料率 */
    private double before_sprv_rate;

    /** 変更後SPリボ利用限度額 */
    private int after_sprv_limit_amount;

    /** 変更後SPリボ初回付利免除 */
    private String after_sprv_init_furi_menjo;

    /** 変更後SPリボ毎月返済額 */
    private int after_sprv_monthly_payamount;

    /** 変更後SPリボ手数料率 */
    private double after_sprv_rate;
}
