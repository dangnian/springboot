package com.dangnian.springboot.common.util;

import org.apache.commons.lang3.ObjectUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>对象类基础工具类<p/>
 *
 * @Author nian.qiu
 * @Date 2018/8/13 14:34
 * @Version 1.0.0
 */
public class ObjectBaseUtils {

    /**
     * 获得默认代理对象
     *
     * @return ObjectUtils
     */
    public static  ObjectUtils getProxy(){
       return new ObjectUtils();
    }

    /**
     * 如果对象为Null,则用默认值替换
     *
     * @param object
     * @param defaultValue
     * @return  T
     */
    public static <T> T defaultIfNull(T object, T defaultValue) {
       return ObjectUtils.defaultIfNull(object,defaultValue);
    }

    /**
     * 判断对象是NULL
     *
     * @param object
     * @return true: 为NULL false: 不为NULL
     */
    public static boolean isEmpty(Object object) {
        return  !isNotEmpty(object);
    }

    /**
     * 判断对象不是NULL
     *
     * @param object
     * @return true: 不为NULL false: 为NULL
     */
    public static boolean isNotEmpty(Object object) {
        return ObjectUtils.allNotNull(object);
    }


    /**
     * 判断容器中存在NULL
     *
     * @param valueList 容器对象
     * @return true: 都为NULL false: 至少存在一个不为NULL
     */
    public static boolean isEmpty(List<Object> valueList) {
        return  !isNotEmpty(valueList);
    }

    /**
     * 判断容器中不存在NULL
     *
     * @param valueList 容器对象
     * @return true: 都不为NULL false: 至少存在一个为NULL
     */
    public static boolean isNotEmpty(List<Object> valueList) {
        if(CollectionBaseUtils.isNotEmpty(valueList)){
            return ObjectUtils.allNotNull(valueList.toArray(new Object[valueList.size()]));
        }
        return false;
    }

    public static <T> void convertEmptyStringToNull(T instance, Class<T> classz) {
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(classz);
        } catch (IntrospectionException e) {
        }
        if(beanInfo==null){
            throw new RuntimeException(classz.toString()+"未定义？");
        }
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        if (proDescrtptors != null && proDescrtptors.length > 0) {
            for (PropertyDescriptor propDesc : proDescrtptors) {

                // String type
                if (String.class.getName().equals(
                        propDesc.getPropertyType().getName())) {
                    Method read = propDesc.getReadMethod();
                    String value = null;
                    try {
                        value = (String) read.invoke(instance);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    if ("".equals(value)) {
                        Method write = propDesc.getWriteMethod();
                        try {
                            write.invoke(instance, (String) null);
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }
}