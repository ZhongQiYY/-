package com.zhong.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {
    /**
     * 进行类型转换的方法
     */
    @Override
    public Date convert(String source) {
            // 判断
            if(source == null) {
                throw new RuntimeException("参数不能为空");
            }
            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            // 解析字符串
                return df.parse(source);
            } catch (Exception e) {
                throw new RuntimeException("类型转换错误");
            }
    }
}
