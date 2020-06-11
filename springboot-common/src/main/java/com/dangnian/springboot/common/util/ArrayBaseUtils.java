package com.dangnian.springboot.common.util;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * <p>数组类基本工具<p/>
 *
 * @Author nian.qiu
 * @Date 2018/8/13 14:37
 * @Version 1.0.0
 */
public class ArrayBaseUtils {
    /**
     * 获得默认代理对象
     *
     * @return ObjectUtils
     */
    public static ArrayUtils getProxy(){
        return new ArrayUtils();
    }

    /**
     * 数组方向
     *
     * @param array
     */
    public static void reverse(Object[] array){
        ArrayUtils.reverse(array);
    }

    /**
     * 判断数组为空
     *
     * @param array 数组
     * @return
     */
    public static <T> boolean isEmpty(T[] array) {
        return ArrayUtils.isEmpty(array);
    }

    /**
     * 判断数组不为空
     *
     * @param array 数组
     * @return
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    /**
     * 添加数组元素
     *
     * @param array1  原数组
     * @param array2  待添加的数组
     * @return
     */
    public static <T> T[] addAll(T[] array1, T... array2) {
        return ArrayUtils.addAll(array1,array2);
    }

    /**
     * 添加数组元素
     *
     * @param array   原数组
     * @param element 待添加的元素
     * @return
     */
    public static <T> T[] add(T[] array, T element) {
       return ArrayUtils.add(array,element);
    }

    /**
     * 移除数组元素
     *
     * @param array   原数组
     * @param element 待移除的元素
     * @return
     */
    public static <T> T[] removeElement(T[] array, Object element) {
        return ArrayUtils.removeElement(array,element);
    }

    /**
     * 移除数组元素
     *
     * @param array   原数组
     * @param index   待移除指针
     * @return
     */
    public static <T> T[] remove(T[] array, int index) {
        return ArrayUtils.remove(array,index);
    }

    /**
     * 判断两个数值内容是否相等
     *
     * @param array1
     * @param array2
     * @return
     */
    public static boolean isEquals(Object[] array1, Object[] array2){
        return Arrays.equals(array1,array2);
    }
}