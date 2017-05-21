package me.kiuber.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created 2017/5/17 0017 20:44
 * Author Kiuber
 * Description
 */

public class TimeUtil {
    private static TimeUtil instance;
    private String format = "yyyy-MM-dd HH:mm:ss";

    public static TimeUtil get() {
        if (instance == null) {
            instance = new TimeUtil();
        }
        return instance;
    }

    /**
     * 格式化当前时间
     *
     * @param format
     * @return
     */
    public String timeFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(new Date());
    }

    /**
     * 格式化指定时间
     *
     * @param format
     * @param date
     * @return
     */
    public String dateFormat(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * 获取当前时间，采用默认格式
     *
     * @return
     */
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间，指定格式
     * @param format
     * @return
     */
    public String getTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(new Date());
    }
}
