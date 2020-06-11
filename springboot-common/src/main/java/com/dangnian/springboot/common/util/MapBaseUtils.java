package com.dangnian.springboot.common.util;


import org.apache.commons.collections4.MapUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * <p>映射类基本工具<p/>
 *
 * @Author nian.qiu
 * @Date 2018/8/13 14:37
 * @Version 1.0.0
 */
public class MapBaseUtils {


    /**
     * 获得空排序映射
     *
     * @return
     */
    public static SortedMap emptySortedMap(){
        return  MapUtils.EMPTY_SORTED_MAP;
    }

    /**
     * 判断映射是空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        return MapUtils.isEmpty(map);
    }

    /**
     * 判断映射不是空
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map map) {
        return MapUtils.isNotEmpty(map);
    }

    /**
     * 同步Map
     *
     * @param map
     * @return
     */
    public static Map synchronizedMap(Map map) {
        return MapUtils.synchronizedMap(map);
    }

    /**
     * 不可变Map
     *
     * @param map
     * @return
     */
    public static Map unmodifiableMap(Map map) {
        return MapUtils.unmodifiableMap(map);
    }

    /**
     * Bean --> Map
     * 利用Introspector和PropertyDescriptor 将Bean --> Map
     *
     * @param obj
     */
    public static Map<String, Object> bean2Map(Object obj)
            throws RuntimeException {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return map;
    }
}