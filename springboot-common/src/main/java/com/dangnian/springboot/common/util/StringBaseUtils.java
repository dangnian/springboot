package com.dangnian.springboot.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * <p>字符类基础工具类<p/>
 *
 * @Author nian.qiu
 * @Date 2018/8/13 14:34
 * @Version 1.0.0
 */
public class StringBaseUtils {

    protected final static Logger LOGGER = LoggerFactory.getLogger(StringBaseUtils.class);

    /**
     * 获得默认代理对象
     *
     * @return StringUtils
     */
    public static StringUtils getProxy(){
        return new StringUtils();
    }

    /**
     * 判断字符串是否空
     *
     * @param string
     * @return true：空字符串 false：非空字符串
     */
    public static boolean isEmpty(String string) {
        return StringUtils.isEmpty(string);
    }

    /**
     * 判断是否为空字符串
     *
     * @param string
     * @return true：非空字符串 false：空字符串
     */
    public static boolean isNotEmpty(String string){
        return StringUtils.isNotEmpty(string);
    }

    /**
     * 判断字符串是空白
     *
     * @param string
     * @return
     */
    public static boolean isBlank(String string) {
       return StringUtils.isBlank(string);
    }

    /**
     * 判断字符串不为空白
     *
     * @param string
     * @return
     */
    public static boolean isNotBlank(String string) {
        return StringUtils.isNotBlank(string);
    }


    /**
     * 左、右、两边填充字符
     *
     * @param str      待填充的字符串，可以为null
     * @param fillChar 填充的字符
     * @param fillSide ['left','right','both']
     *                 填充的方向，
     * @param size     输出字符串的固定byte长度。
     * @return String
     */
    public static String charFill(String str, char fillChar, String fillSide, int size) {
        str = (str == null) ? "" : str;
        StringBuilder sb = new StringBuilder(str);
        int len = str.length();
        if (len >= size) {
            return (("left".equals(fillSide)) ? str.substring(len - size) : str.substring(0, size));
        }
        int n = size - len;
        if ("left".equals(fillSide)) {
            for (int i = 0; i < n; ++i) {
                sb.insert(0, fillChar);
            }
        } else if ("right".equals(fillSide)) {
            for (int i = 0; i < n; ++i) {
                sb.append(fillChar);
            }
        } else if ("both".equals(fillSide)) {
            for (int i = 0; i < n; ++i) {
                if (i % 2 == 0) {
                    sb.insert(0, fillChar);
                } else {
                    sb.append(fillChar);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 按字符模式截取字符串
     *
     * @param string
     * @param beginIndex
     * @param endIndex
     * @return
     */
    public static String sub(String string, int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        if (string != null && string.length() <= endIndex){
            return string;
        }else if (string != null && string.length() > endIndex){
            return string.substring(beginIndex, endIndex);
        } else{
            return null;
        }
    }

    /**
     * 字符串的字节长度
     *
     * @param src
     * @param charsetName
     * @return
     */
    public static int lengthByByte(String src, String charsetName) {
        if(StringUtils.isEmpty(src)){
            return  0;
        }
        try {
            byte[] srcBytes = src.getBytes(charsetName);
            return srcBytes.length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 字符串的字符长度
     *
     * @param src
     * @param charsetName
     * @return
     */
    public static int length(String src, String charsetName) {
        if(StringUtils.isEmpty(src)){
            return  0;
        }
        return src.length();
    }

    /**
     * 以字节截取
     *
     * @param src           原字符串
     * @param charsetName   字节编码
     * @param beginIndex    截取起始位置
     * @param endIndex      截取截止位置
     * @return
     */
    public static String subByByte(String src, String charsetName, int beginIndex, int endIndex) {
        if(StringUtils.isEmpty(src)){
            return  null;
        }
        try {
            byte[] srcBytes = src.getBytes(charsetName);
            if(srcBytes.length >= endIndex ){
                return new String(ByteBaseUtils.sub(srcBytes,beginIndex,endIndex),charsetName) ;
            } else {
                return src;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分隔字符串
     *
     * @param str
     * @param separator
     * @return
     */
    public static List<String> split(String str, String separator){
        List<String> list = null;
        if(isNotEmpty(str)){
            String[] strArr = str.split(separator);
            if(strArr != null){
                for (int i = 0; i < strArr.length; i++) {
                    strArr[i] = strArr[i].trim();
                }
            }
            list = Arrays.asList(strArr);
        }
        return list;
    }

    /**
     * 分隔字符串
     *
     * @param str
     * @param separator
     * @return
     */
    public static String[] splitToArray(String str, String separator){
        if(isNotEmpty(str)){
            String[] strArr = str.split(separator);
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = trim(strArr[i]);
            }
            return strArr;
        }
        return null;
    }

    /**
     * 获得唯一编号字符
     *
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString();
    }

    /**
     * 字符串去头去尾
     *
     * @param str
     * @return
     */
    public static String trim(String str){
        return  str == null?null:str.trim();
    }

    /**
     * 判断字符范围
     *
     * @param str
     * @param beginStr
     * @param endStr
     * @return
     */
    public static boolean between(String str, String beginStr, String endStr) {
        boolean between = false;
        if (beginStr != null && endStr != null && str != null && str.compareTo(beginStr) >= 0 && str.compareTo(endStr) <= 0) {
            between = true;
        }
        return between;
    }

    /**
     * 格式化账号
     *
     * @param accountNo  账号
     * @param bit        分隔位数
     * @return
     */
    public static String formatAccountNo(String accountNo, int bit){
       return accountNo.replaceAll("\\d{"+bit+"}(?!$)", "$0 ");
    }

    /**
     * 从左侧开始按字节截取字符串
     * @param src   原字符串
     * @param len   截取长度
     * @return 字字符串
     */
    public static String leftByByte(String src, int len) {
        return StringBaseUtils.leftByByte(src, "utf-8", len);
    }

    /**
     * 从左侧开始按字节截取字符串
     * @param src   原字符串
     * @param charsetName   字符编码
     * @param len   截取长度
     * @return 字字符串
     */
    public static String leftByByte(String src, String charsetName, int len) {
        if(StringUtils.isEmpty(src)){
            return src;
        }
        try {
            //长度为0或负数，则返回null
            if(len <= 0) {
                return null;
            }
            //判断字符长度是否超过要求长度
            byte[] srcBytes = src.getBytes(charsetName);
            if(srcBytes.length <= len){
                return src;
            }
            String subStr = new String(ByteBaseUtils.sub(srcBytes, 0, len), charsetName);
            //消除最后一个可能为乱码的字符
            int endIndex = subStr.length() - 1;
            if(subStr.charAt(endIndex) != src.charAt(endIndex)){
                subStr = subStr.substring(0, endIndex);
            }
            return subStr;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String substringAfterLast(String str, String separator) {
        return StringUtils.substringAfterLast(str, separator);
    }

    public static boolean startsWith(CharSequence sequence, CharSequence prefix) {
        return StringUtils.startsWith(sequence, prefix);
    }

    public static boolean startsWithAny(CharSequence sequence, CharSequence... searchStrings) {
        return StringUtils.startsWithAny(sequence, searchStrings);
    }

    /**
     * 多字符连接,返回单值
     *
     * @param separator  连接方式
     * @param values     连接字符
     * @return
     */
    public static String join(String separator, String... values) {
        return StringUtils.join(values, separator);
    }

    /**
     * 连接字符，返回多值
     *
     * @param separator
     * @param prefix
     * @param valueList
     * @return
     */
    public static List<String> joinList(String separator, String prefix, List<String> valueList) {
        List<String> joinValueList = new ArrayList<>();
        for (String value : valueList) {
            joinValueList.add(join(separator, prefix, value));
        }
        return joinValueList;
    }

    public static String toAppend(List<String> list) {
        StringBuffer sb = new StringBuffer();
        if (null != list) {
            for (String obj : list) {
                if ((null != obj) && (!("".equals(obj)))) {
                    sb.append(obj).append(",");
                }
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        }
        return null;
    }

    /**
     *
     * <pre>
     *   把逗号隔开的字符串转换成list集合对象
     * </pre>
     *
     * @param strParam
     * @return
     */
    public static List<String> splitStringToList(String strParam){
        List<String> list = new ArrayList<String>();
        try{
            if(!isEmpty(strParam)){
                String[] strs = strParam.split(",");
                for(String str :strs){
                    list.add(str);
                }
            }
        }catch(Exception e){
            LOGGER.error("字符串按逗号拆分错误", strParam,e);
        }
        return list;
    }

    public static String getRandowForLength(Integer lenth, Integer curValue){
        String tempValue = String.valueOf(curValue);
        //使用指定的格式字符串和参数返回一个格式化字符串.
        //此处指若tempValue长度不足lenth位，则在左边用0来填充。
        return String.format("%0"+lenth+"d", Integer.parseInt(tempValue));
    }

    /**
     * 对象转成字符串
     * @param obj
     * @return
     */
    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

    /**
     * 校验返回
     *
     * @param expect
     * @param returnResult
     * @return
     */
    public static String checkReturn(boolean expect, String returnResult){
        if(!expect){
            return returnResult;
        }
        return "";
    }

    public static boolean isEqual(String sourceStr, String targetStr){
        if(isNotEmpty(sourceStr) && !sourceStr.equals(targetStr)){
            return false;
        } else if(isNotEmpty(targetStr) && !targetStr.equals(sourceStr)){
            return false;
        }
        return true;
    }

    /**
     * 截取指定字符串后面的数据
     * 如果指定字符串不存在，则返回完整字符串
     * @param text
     * @param separator
     * @return
     */
    public static String subAfter(String text, String separator) {
        if(isEmpty(text)) {
            return text;
        } else if(separator == null) {
            return "";
        } else {
            int pos = text.indexOf(separator);
            return pos == -1?text:text.substring(pos + separator.length());
        }
    }

    /**
     * 去掉小数点后面多余的0
     * @param sourceVal 原始值
     * @return
     */
    public static String stripTrailingZeros(String sourceVal){
        if(StringUtils.isNotBlank(sourceVal)){
            try {
                BigDecimal value = new BigDecimal(sourceVal);
                BigDecimal noZeros = value.stripTrailingZeros();
                return noZeros.toPlainString();
            }catch (Exception e){
                LOGGER.error("stripTrailingZeros error.",e);
                return sourceVal;
            }
        }
        return null;
    }
}