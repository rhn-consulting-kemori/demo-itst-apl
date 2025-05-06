package com.redhat.example.type;

import lombok.Data;

@Data
public class YakujoHenkoTypeRequest {
    /** 顧客番号 */
    private String customer_number;

    /** 変更FLG */
    private String sprv_limit_amount_flg;
    private String sprv_init_furi_menjo_flg;
    private String sprv_monthly_payamount_flg;
    private String sprv_rate_flg;

    /** SPリボ利用限度額 */
    private int sprv_limit_amount;

    /** SPリボ初回付利免除 */
    private String sprv_init_furi_menjo;

    /** SPリボ毎月返済額 */
    private int sprv_monthly_payamount;

    /** SPリボ手数料率 */
    private double sprv_rate;
}
