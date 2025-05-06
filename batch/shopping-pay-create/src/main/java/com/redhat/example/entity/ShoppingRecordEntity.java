package com.redhat.example.entity;

import lombok.Data;

@Data
public class ShoppingRecordEntity {

    /** 売上番号 */
    private int shopping_number;

    /** 顧客番号 */
    private String customer_number;

    /** 利用カード番号 */
    private String use_card_number;

    /** 請求締日 */
    private String seikyu_shimebi;

    /** 支払方法 */
    private String pay_type;

    /** 利用日 */
    private String use_date;

    /** 利用加盟店 */
    private String shop_name;

    /** 利用金額 */
    private int use_amount;

}
