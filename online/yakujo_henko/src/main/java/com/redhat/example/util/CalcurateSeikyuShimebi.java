package com.redhat.example.util;

import com.redhat.example.util.CalcurateDateUtil;

public class CalcurateSeikyuShimebi {

    public String getSeikyuShimebi(String use_date, String simu_date) {
        // CalcurateDateUtil
        CalcurateDateUtil cal_date_util = new CalcurateDateUtil();

        // 当月
        String togetsu_shimebi = simu_date.substring(0,6) + "15";

        // 翌月
        String yokugetsu_shimebi = cal_date_util.string_convert(
            cal_date_util.getMonthAddDate(cal_date_util.date_convert(simu_date.substring(0,6) + "15"), 1)
        );

        System.out.println("当月：" + togetsu_shimebi + "、翌月：" + yokugetsu_shimebi);

        // 想定日：20-31
        if(Integer.parseInt(simu_date.substring(6,8)) > 19) {
            return yokugetsu_shimebi;

        // 想定日：01-19
        } else {
            // 利用日：16-19
            if(use_date.compareTo(togetsu_shimebi) > 0){
                return yokugetsu_shimebi;

            // 利用日：01-15
            } else {
                return togetsu_shimebi;
            }
        }
    }

    public String getYakujoHenkoSeikyuShimebi(String simu_date) {
        // CalcurateDateUtil
        CalcurateDateUtil cal_date_util = new CalcurateDateUtil();

        // 当月
        String togetsu_shimebi = simu_date.substring(0,6) + "15";

        // 翌月
        String yokugetsu_shimebi = cal_date_util.string_convert(
            cal_date_util.getMonthAddDate(cal_date_util.date_convert(simu_date.substring(0,6) + "15"), 1)
        );

        System.out.println("当月：" + togetsu_shimebi + "、翌月：" + yokugetsu_shimebi);

        // 想定日：20-31
        if(Integer.parseInt(simu_date.substring(6,8)) > 19) {
            return yokugetsu_shimebi;

        // 想定日：01-19
        } else {
            return togetsu_shimebi;
        }
    }

}
