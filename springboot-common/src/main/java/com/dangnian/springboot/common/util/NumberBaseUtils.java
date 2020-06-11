package com.dangnian.springboot.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberBaseUtils {
    private static Logger LOGGER  = LoggerFactory.getLogger(NumberBaseUtils.class);

    public static Integer toInteger(String str) {
        try {
            if(str == null || str.trim().isEmpty()) {
                return null;
            }
            return Integer.valueOf(str);
        } catch (Exception e) {
            LOGGER.error("字符串转换Integer异常", e);
        }
        return null;
    }
}