package com.dangnian.springboot.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>数值类基础工具类<p/>
 *
 * @Author nian.qiu
 * @Date 2019/5/14 14:34
 * @Version 1.0.0
 */
public class BigDecimalBaseUtils {

    private static final Logger logger = LoggerFactory.getLogger(BigDecimalBaseUtils.class);
    /**
     * 字符转化为数值
     *
     * @param value
     * @return
     */
    public static BigDecimal string2BigDecimal(String value){
        if(StringBaseUtils.isEmpty(value)){
            return BigDecimal.ZERO ;
        }
        try{
            return BigDecimal.valueOf(Double.valueOf(value)) ;
        }catch (Exception e){
            logger.error("parse string to number failed! value={}", value, e);
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal newBigDecimal(Long value) {
        return new BigDecimal(value);
    }

    public static BigDecimal newBigDecimal(String value) {
        return new BigDecimal(value);
    }

    /**
     * 判断是否相等
     *
     * @param value1
     * @param value2
     * @return
     */
    public static boolean  equal(BigDecimal value1, BigDecimal value2){
        if(value1 == null || value2 == null){
            return false;
        }
        return value1.compareTo(value2) == 0;
    }

    /**
     * 判断是否误差范围允许内相等
     *
     * @param value1
     * @param value2
     * @param errorRange
     * @return
     */
    public static boolean  equalWithErrorRange(BigDecimal value1, BigDecimal value2, BigDecimal errorRange){
        if (value1 == null || value2 == null) {
            return true;
        }
        if (errorRange == null) {
            errorRange = BigDecimal.ZERO;
        }
        if(errorRange.compareTo(BigDecimal.ZERO) == 0){
            return equal(value1,value2);
        } else {
            return value1.subtract(value2).abs().compareTo(errorRange.abs()) < 0;
        }
    }

    /**
     * 设置数据精度
     *
     * @param value
     * @param newScale
     * @param roundingMode
     * @return
     */
    public static BigDecimal setScale(BigDecimal value, int newScale, RoundingMode roundingMode){
        return value.setScale(newScale,roundingMode);
    }

    /**
     * 保留2位精度，四舍五入
     *
     * @param value
     * @return
     */
    public static BigDecimal setScaleWithTwo(BigDecimal value){
        return setScale(value, 2, RoundingMode.HALF_UP);
    }

    /**
     * 保留3位精度，四舍五入
     *
     * @param value
     * @return
     */
    public static BigDecimal setScaleWithThree(BigDecimal value){
        return setScale(value, 3, RoundingMode.HALF_UP);
    }

    /**
     * 保留4位精度，四舍五入
     *
     * @param value
     * @return
     */
    public static BigDecimal setScaleWithFour(BigDecimal value){
        return setScale(value,4, RoundingMode.HALF_UP);
    }

    /**
     * 保留6位精度，四舍五入
     *
     * @param value
     * @return
     */
    public static BigDecimal setScaleWithSix(BigDecimal value){
        return setScale(value,6, RoundingMode.HALF_UP);
    }


    /**
     * 保留8位精度，四舍五入
     *
     * @param value
     * @return
     */
    public static BigDecimal setScaleWithEight(BigDecimal value){
        return setScale(value,8, RoundingMode.HALF_UP);
    }
    
    /**
     * 
     * @param value
     * @return 入参为空 返回 0
     */
    public static BigDecimal dealNullBigDecimal(BigDecimal value) {
    	
    	return value==null? BigDecimal.ZERO:value;
    }

    /**
     * 转换为负数
     * @param value
     * @return
     */
    public static BigDecimal negate(BigDecimal value) {
        return value != null?value.negate():null;
    }

    public static boolean isNegative(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) < 0;
    }

    /**
     * 是否为非负数
     * @param value
     * @return
     */
    public static boolean isNonNegative(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) >= 0;
    }

    public static boolean notEqualsZero(BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) != 0;
    }

    public static boolean gteZero(BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) >= 0;
    }

    public static <T> int multipleToInt(BigDecimal value, Long multiple) {
        return value.multiply(BigDecimal.valueOf(multiple)).intValue();
    }
}