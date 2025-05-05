package com.redhat.example.rule;

// Spring
import org.springframework.stereotype.Component;

// BUsiness Object
import com.redhat.example.type.YakujoHenkoTypeRequest;

@Component
public class YakujoHenkoCheckRule {
    public boolean check(YakujoHenkoTypeRequest request) {
        /** 顧客番号 */
        if(request.getCustomer_number() == null || request.getCustomer_number().length() != 10) {
            return false;
        }

        /** SPリボ利用限度額 */
        if(request.getSprv_limit_amount_flg().equals("1") && request.getSprv_limit_amount() == 0) {
            return false;
        }

        /** SPリボ初回付利免除 */
        if(request.getSprv_init_furi_menjo_flg().equals("1")) {
            if(request.getSprv_init_furi_menjo().equals("0") || request.getSprv_init_furi_menjo().equals("1")) {
            } else {
                return false;
            }
        }

        /** SPリボ毎月返済額 */
        if(request.getSprv_monthly_payamount_flg().equals("1") && request.getSprv_monthly_payamount() == 0) {
            return false;
        }

        /** SPリボ手数料率 */
        if(request.getSprv_rate_flg().equals("1") && request.getSprv_rate() == 0) {
            return false;
        }

        /** 変更対象が1つあること */
        if(request.getSprv_limit_amount_flg().equals("0") && request.getSprv_init_furi_menjo_flg().equals("0") && request.getSprv_monthly_payamount_flg().equals("0") && request.getSprv_rate_flg().equals("0")) {
            return false;
        }
        return true;
    } 
}
