package com.erp.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.erp.constant.Constant;
import com.erp.util.StringUtil;

/**
 * 
 * @author liuyang
 *
 * @since 2015年12月11日下午3:44:47
 */
public class DateUtil{
    public final static String YYYYMMDD = "yyyy-MM-dd";
    public final static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYYMMDDHHMMSSSSS = "yyyy-MM-dd HH:mm:ss SSS";
    public final static String SXF_TIME = "yyyyMMddHHmmss";
    public final static String SXF_TIME_LONG = "yyyyMMddHHmmssSSS";
    private final static int DAY_TIME = 24 * 60 * 60 * 1000;

    /**
     * 根据传入的Date和转换格式对日期进行格式化，并返回字符串表式形式
     * 
     * @param date
     * @param format
     * @return
     */
    public static String parse(Date date, String format) {
        if (null == date) {
            return Constant.EMPTY_STRING;
        }

        if (StringUtil.isBlank(format)) {
            format = YYYYMMDD;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date).toString();
        } catch (Exception ex) {
            return Constant.EMPTY_STRING;
        }
    }


    /**
     * 计算两个日期之间相隔的天数，date - anotherDate
     * 
     * @param date
     * @param anotherDate
     * @return 两个日期之间相隔的天数
     */
    public static int getIntervalDay(Date dateFront, Date dateBack) {
        if (null == dateFront || null == dateBack)
            return 0;

        long time = dateBack.getTime() - dateFront.getTime();
        return (int) (time / DAY_TIME);
    }

    /**
     * 日期加减
     * 
     * @param date
     * @param day
     * @return 返回加day天后的日期
     */
    public static Date addDay(Date date, int day) {
        if (null == date)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 将字符串形式的日期形式序转为日期对象
     * 
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parse(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 得到给定月份该月第一天时间戳
     * 
     * @param month
     * @return
     */
    public static long getFirstDayTime(String month) {
        // TODO: 利用常量
        String[] detail = month.split("-");
        Calendar cal = Calendar.getInstance();
        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
        cal.set(Calendar.YEAR, Integer.parseInt(detail[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(detail[1]));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date first = cal.getTime();
        return parse(parse(first, null), YYYYMMDD).getTime();
    }

    /**
     * 得到给定月份该月最后一天时间戳
     * 
     * @param month
     * @return
     */
    public static long getLastDayTime(String month) {
        // TODO: 利用常量
        String[] detail = month.split("-");
        Calendar cal = Calendar.getInstance();
        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
        cal.set(Calendar.YEAR, Integer.parseInt(detail[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(detail[1]));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.add(Calendar.DATE, 1);
        Date lastDate = cal.getTime();
        return parse(parse(lastDate, null), YYYYMMDD).getTime() - 1;
    }

    /**
     * 获得三个月之前日期
     * 
     * @return
     */
    public static Date getThreeMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -3);
        return calendar.getTime();
    }
}
