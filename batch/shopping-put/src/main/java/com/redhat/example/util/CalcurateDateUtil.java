package com.redhat.example.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class CalcurateDateUtil {
    
    /** 日付変換 */
    public Date date_convert(String strDate) {
        try {
            strDate = strDate.substring(0,4) + "-" + strDate.substring(4,6) + "-" + strDate.substring(6,8);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            Date result = df.parse(strDate);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    /** 文字列変換 */
    public String string_convert(Date baseDate) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            return df.format(baseDate);
        } catch (Exception e) {
            return null;
        }
    }

    // 日付計算(月)
    public Date getMonthAddDate(Date base_date, int date_length) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(base_date);
        calendar.add(Calendar.MONTH, date_length);
        return calendar.getTime();
    }

    /** 日付差分 */
    public long date_between(String strDate1, String strDate2) {
        try {
            LocalDate date1 = LocalDate.of(
                Integer.parseInt(strDate1.substring(0,4)), 
                Integer.parseInt(strDate1.substring(4,6)), 
                Integer.parseInt(strDate1.substring(6,8))
            );
            LocalDate date2 = LocalDate.of(
                Integer.parseInt(strDate2.substring(0,4)), 
                Integer.parseInt(strDate2.substring(4,6)), 
                Integer.parseInt(strDate2.substring(6,8))
            );
            long localDiffDays1 = ChronoUnit.DAYS.between(date1, date2);
            return localDiffDays1;
        } catch (Exception e) {
            return 0;
        }
    }

}
