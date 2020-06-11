package com.dangnian.springboot.common.util;

import java.util.regex.Pattern;

/**
 * <p>字符操作工具类
 * <p/>
 *
 * @Author nian.qiu
 * @Date 2018/6/8 15:36
 * @Version 1.0.0
 */
public class ByteBaseUtils {

    /**
     * 截取字节
     *
     * @param beginIndex 截取起始位置
     * @param endIndex   截取截止位置
     * @return
     */
    public static byte[] sub(byte[] srcArray, int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        if(beginIndex > endIndex){
            return srcArray;
        }
        if (srcArray != null && srcArray.length <= endIndex){
            return srcArray;
        } else if (srcArray != null && srcArray.length > endIndex){
            byte[]  destArray = new byte[endIndex-beginIndex];
            System.arraycopy(srcArray,beginIndex,destArray,0,destArray.length);
            return destArray;
        } else{
            return null;
        }
    }

    /**
     * ASCII -> BCD
     * <p>
     * 原理：<br>
     * 例如： Ascii: 1234 转换为 BCD: **<br>
     *
     * 字符 0123456789:;<=>? 共16个字符的Hex码为[0x3*]其中3表示前4位，*表示后4位，*为0-F，所以在压缩时，
     * 舍弃了字符的前4位，而将两个字符的后4位来组成一个新8位的字符。
     * <p>
     *
     * @param bs
     *            待转换的ascii字节数组，必须为偶数。
     * @return 转为bcd后的字节数组。
     */
    public static byte[] ascii2bcd(byte[] bs) {
        byte[] res = new byte[bs.length / 2];
        for (int i = 0, n = bs.length; i < n; i += 2){
            res[i / 2] = (byte) ((bs[i] << 4) | (bs[i + 1] & 0x0f));
        }
        return res;
    }

    /**
     * BCD->ASCII原理：
     * <p>
     * 拆分byte的前4位和后4位，然后将这两个4位前面补上0x30(ascii中0123456789:; <=>的共同前4位) 组成两个新的byte.
     * <p>
     * @param bs
     *            待转换的bcd字节数组。
     * @return 转换为ascii后的字节数组。
     */
    public static byte[] bcd2ascii(byte[] bs) {
        byte[] res = new byte[bs.length * 2];
        for (int i = 0, n = bs.length; i < n; i++) {
            res[i * 2] = (byte) (((bs[i] & 0xf0) >> 4) | 0x30);
            res[i * 2 + 1] = (byte) ((bs[i] & 0x0f) | 0x30);
        }
        return res;
    }

    /**
     * Ascii转换为16进制方式： <br>
     * 例如：字符AB转换为0xAB对应的字符。
     *
     * @param bs
     *            待转换的byte数组
     * @return 压缩为16进制后的byte数组
     */
    public static byte[] ascii2hex(byte[] bs) {
        byte[] res = new byte[bs.length / 2];
        for (int i = 0, n = bs.length; i < n; i += 2) {
            res[i / 2] = (byte) (Integer.parseInt(new String(bs, i, 2), 16));
        }
        return res;
    }

    /**
     * 16进制转换为Ascii
     * <p>
     * 例如： 字符0xAB转换为Ascii的两个字符AB
     *
     * @param bs
     *            待转换的16进制的数组
     * @return 解压后的Ascii数组
     */
    public static byte[] hex2ascii(byte[] bs) {
        byte[] res = new byte[bs.length * 2];
        for (int i = 0; i < bs.length; i++) {
            //需要将大于127的byte转换为正的int型
            int ti = bs[i];
            ti = ti < 0 ? ti + 256 : ti;
            String t = Integer.toHexString(ti);
            if (t.length() < 2) t = "0" + t;
            res[i * 2] = (byte) t.charAt(0);
            res[i * 2 + 1] = (byte) t.charAt(1);
        }
        return res;
    }

    /**
     * 自动类型转换控制器
     * <p>
     * 目前支持4中类型转换： <br>
     * Ascii -> BCD <br>
     * Ascii -> Hex <br>
     * BCD -> Ascii <br>
     * Hex -> Ascii <br>
     * self -> self <br>
     *
     * @param bs
     *            代转换的byte数组
     * @param sourceType ["asc","bcd","hex","binary"]
     *            原编码方式
     * @param targetType ["asc","bcd","hex","binary"]
     *            目标编码方式
     * @return byte[]
     */
    public static byte[] transit(byte[]bs, String sourceType, String targetType) {
        try {
            String s = sourceType.toLowerCase();
            String t = targetType.toLowerCase();
            if(s.equals("binary")){
                s = "ascii";
            }
            if(t.equals("binary")){
                t = "ascii";
            }
            if (s.equals("ascii") && t.equals("bcd")) {
                return ascii2bcd(bs);
            } else if (s.equals("ascii") && t.equals("hex")) {
                return ascii2hex(bs);
            } else if (s.equals("bcd") && t.equals("ascii")) {
                return bcd2ascii(bs);
            } else if (s.equals("hex") && t.equals("ascii")) {
                return hex2ascii(bs);
            } else if (s.equals(t)) {
                return bs;
            }
            throw new RuntimeException("不支持从类型" + s + "向" + t + "的转换。");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 组装BitMap <p>
     * 将代表位图的boolean数组转化为长度length/8后的byte数组。
     *
     * @param source
     * @return byte[]
     */
    public static byte[] mackBitMap(boolean[] source) {
        int n = source.length / 8;
        byte[] bs = new byte[n];
        for (int i = 0; i < n; i++) {
            char c = 0x00;
            for (int j = 0; j < 8; j++){
                c = source[8 * i + j] ? (char) (c | (1 << (7 - j))) : c;
            }
            bs[i] = (byte) c;
        }
        return bs;
    }

    /**
     * 拆分BitMap <p>
     * 将字节数组转化为长度length*8之后的boolean位图数组
     *
     * @param bs
     * @return boolean[]
     */
    public static boolean[] splitBitMap(byte[] bs) {
        boolean[] res = new boolean[bs.length * 8];
        for (int i = 0; i < bs.length; i++) {
            int x = bs[i] < 0 ? (bs[i] + 256) : bs[i];
            for (int j = 0; j < 8; j++){
                res[i * 8 + j] = (x & 1 << (7 - j)) == (1 << (7 - j)) ? true : false;
            }
        }
        return res;
    }


    /**
     * 对待转换为hex的ascii码制字符串的验证模板
     */
    public static Pattern PATTERN_HEX = Pattern.compile("^[\\d|a-f|A-F]*$");

    /**
     * 将16进制的字符串转换为byte[]
     *
     * @param  hexStr   16进制字符串
     * @return byte[]   byte[]数组
     */
    public static byte[] hex2bytes(String hexStr) {
        if(!(PATTERN_HEX.matcher(hexStr).matches()) || hexStr.length()%2!=0){
            throw new RuntimeException("16进制字符串不能转换为byte[]，包含了非法字符或长度不是2的倍数。原字符串："+hexStr);
        }
        byte[]bs=new byte[hexStr.length()/2];
        for(int i=0;i < hexStr.length();i += 2){
            int nn= Integer.parseInt(hexStr.substring(i,i+2),16);
            bs[i/2]=(byte)nn;
        }
        return bs;
    }

    /**
     * 将byte[]数组转化为16进制字符串
     *
     * @param  bs      byte[]数组
     * @return String  16进制字符串
     */
    public static String bytes2hex(byte[]bs){
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < bs.length; i++) {
            int nn = bs[i]< 0 ? bs[i] + 256 : bs[i];
            String t = Integer.toHexString(nn).toUpperCase();
            sb.append(t.length()<2?"0"+t:t);
        }
        return sb.toString();
    }
}