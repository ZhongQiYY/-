package com.zhong.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
//    日期转换成字符串
    public static String dateToString(Date date, String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        return sdf.format(date);
    }

//    字符串转换成日期
    public static Date stringToDate(String str, String patt) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        return sdf.parse(str);
    }
}
