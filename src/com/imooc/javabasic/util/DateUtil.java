package com.imooc.javabasic.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ace
 * @create 2020-05-30
 */
public class DateUtil {

    public static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {
        System.out.println(DateUtil.df.get().format(new Date()));
    }

}
