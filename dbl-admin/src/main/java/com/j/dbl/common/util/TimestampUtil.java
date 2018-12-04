package com.j.dbl.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Timestamp工具类
 */
public class TimestampUtil {

    /**
     * 字符转时间
     *
     * @param strDate   时间
     * @param formatStr
     */
    public static Timestamp getStrToTimestamp(String strDate, String formatStr) {

        if (StringUtil.isEmpty(strDate))
            return null;
        if (StringUtil.isEmpty(formatStr))
            formatStr = "yyyy-MM-dd";

        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);

        Date date = new Date();
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        return new Timestamp(date.getTime());
    }

    /**
     * 时间转字符
     *
     * @param ts
     * @param formatStr
     * @return
     */
    public static String getTimestampToStr(Timestamp ts, String formatStr) {

        if (ts == null)
            ts = new Timestamp(System.currentTimeMillis());
        if (StringUtil.isEmpty(formatStr))
            formatStr = "yyyy-MM-dd";

        DateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(ts);

    }

    public static Timestamp getNowTime() {

        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp getAfterNDays(Date date, int n) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);
        Date time = calendar.getTime();
        Timestamp timestamp = new Timestamp(time.getTime());
        return timestamp;
    }

    public static void main(String[] args) {

        System.out.println(getAfterNHoursAsecond(new Timestamp(new Date(2018,07,06,13,00,00).getTime()),24));
//        System.out.println(TimestampUtil.getTimestampToStr(null, "yyyyMMdd"));
    }


    /**
     *
     */

    public static Timestamp getTheLastSecondOfDay(Timestamp timestamp) {
        Date d = new Date(timestamp.getTime());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = format.format(d);
        Date d2 = null;
        try {
            d2 = format.parse(str);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        int dayMis = 1000 * 60 * 60 * 24;//一天的毫秒-1
        long curMillisecond = d2.getTime();//当天的毫秒
        long resultMis = curMillisecond + (dayMis - 1000); //当天最后一秒
        return new Timestamp(resultMis);
    }

    public static Timestamp getAfterNHoursAsecond(Timestamp timestamp,Integer n) {
        Date d = new Date(timestamp.getTime());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        calendar.add(Calendar.HOUR, n);
        Date time = calendar.getTime();
        Timestamp result = new Timestamp(time.getTime()-1000);
        return result;
    }
    public static Timestamp getAfterNHours(Timestamp timestamp,Integer n) {
        Date d = new Date(timestamp.getTime());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        calendar.add(Calendar.HOUR, n);
        Date time = calendar.getTime();
        Timestamp result = new Timestamp(time.getTime());
        return result;
    }
}
