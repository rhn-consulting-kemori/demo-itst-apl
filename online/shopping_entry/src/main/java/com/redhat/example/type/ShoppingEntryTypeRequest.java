package com.redhat.example.type;

import lombok.Data;

@Data
public class ShoppingEntryTypeRequest {

    /** 利用カード番号 */
    private String use_card_number;

    /** 利用日 */
    private String use_date;

    /** 利用加盟店 */
    private String shop_name;

    /** 利用金額 */
    private int use_amount;

    /** 支払方法 */
    private String pay_type;
    
}
