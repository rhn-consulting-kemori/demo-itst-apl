package com.redhat.example.type;

import lombok.Data;

@Data
public class SprvUriageAddTypeResponse {
    /** 顧客番号 */ 
    private String customer_number;

    /** 請求締日 */ 
    private String seikyu_shimebi;

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
}
