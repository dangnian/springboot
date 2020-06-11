package com.dangnian.springboot.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 反射工具类
 */
public class ReflectBaseUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectBaseUtils.class);

    /**
     * Get the property name of a method name. For example the property name of
     * setSomeValue would be someValue. Names not beginning with set or get are
     * not changed.
     * 
     * @param methodName The name to process
     * @return The property name
     */
    public static String getPropertyName(String methodName) {
        if(methodName != null && (methodName.startsWith("get") || methodName.startsWith("set"))) {
            StringBuilder b = new StringBuilder(methodName);
            b.delete(0, 3);
            b.setCharAt(0, Character.toLowerCase(b.charAt(0)));
            return b.toString();
        } else {
            return methodName;
        }
    }

    /**
     * 获得属性的Get方法
     *
     * @param propertyName
     * @return
     */
    public static String getGetPropertyMethod(String propertyName){
        String propertyMethod = propertyName;
        if(propertyName != null && propertyName.trim().length() > 1) {
            StringBuilder b = new StringBuilder();
            b.append("get")
             .append(Character.toUpperCase(propertyName.charAt(0)))
             .append(propertyName.substring(1));
            propertyMethod = b.toString();
        }
        return propertyMethod;
    }

    /**
     * 获得属性的Set方法
     *
     * @param propertyName
     * @return
     */
    public static String getSetPropertyMethod(String propertyName){
        String propertyMethod = propertyName;
        if(propertyName != null && propertyName.trim().length() > 1) {
            StringBuilder b = new StringBuilder();
            b.append("set")
                    .append(Character.toUpperCase(propertyName.charAt(0)))
                    .append(propertyName.substring(1));
            propertyMethod = b.toString();
        }
        return propertyMethod;
    }

    /**
     * Load the given class using the default constructor
     * 
     * @param className The name of the class
     * @return The class object
     */
    public static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch(ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Load the given class using a specific class loader.
     * 
     * @param className The name of the class
     * @param cl The Class Loader to be used for finding the class.
     * @return The class object
     */
    public static Class<?> loadClass(String className, ClassLoader cl) throws ClassNotFoundException {
        return Class.forName(className, false, cl);
    }

    /**
     * Call the no-arg constructor for the given class
     * 
     * @param <T> The type of the thing to construct
     * @param klass The class
     * @return The constructed thing
     */
    public static <T> T callConstructor(Class<T> klass) {
        return callConstructor(klass, new Class<?>[0], new Object[0]);
    }

    /**
     * Call the constructor for the given class, inferring the correct types for
     * the arguments. This could be confusing if there are multiple constructors
     * with the same number of arguments and the values themselves don't
     * disambiguate.
     * 
     * @param klass The class to construct
     * @param args The arguments
     * @return The constructed value
     */
    public static <T> T callConstructor(Class<T> klass, Object[] args) {
        Class<?>[] klasses = new Class[args.length];
        for(int i = 0; i < args.length; i++) {
            klasses[i] = args[i].getClass();
        }
        return callConstructor(klass, klasses, args);
    }

    /**
     * Call the class constructor with the given arguments
     * 
     * @param c The class
     * @param args The arguments
     * @return The constructed object
     */
    public static <T> T callConstructor(Class<T> c, Class<?>[] argTypes, Object[] args) {
        try {
            Constructor<T> cons = c.getConstructor(argTypes);
            return cons.newInstance(args);
        } catch(InvocationTargetException e) {
            throw getCause(e);
        } catch(IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch(NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        } catch(InstantiationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Call the named method
     * 
     * @param obj The object to call the method on
     * @param methodName The name of the method
     * @param classes    the class of method arguments
     * @param args The method arguments
     * @return The result of the method
     */
    public static <T> Object callMethod(Object obj,
                                        String methodName,
                                        Class<?>[] classes,
                                        Object[] args) {
        try {
            Method m = getDeclaredMethod(obj, methodName, classes);
            if(m != null){
                m.setAccessible(true);
                return m.invoke(obj, args);
            }
            return null;
        } catch(InvocationTargetException e) {
            throw getCause(e);
        } catch(IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Get the named method from the class
     * 
     * @param c The class to get the method from
     * @param name The method name
     * @param argTypes The argument types
     * @return The method
     */
    public static <T> Method getMethod(Class<T> c, String name, Class<?>... argTypes) {
        try {
            return c.getDeclaredMethod(name, argTypes);
        } catch(NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Get the root cause of the Exception
     * 
     * @param e The Exception
     * @return The root cause of the Exception
     */
    private static RuntimeException getCause(InvocationTargetException e) {
        Throwable cause = e.getCause();
        if(cause instanceof RuntimeException) {
            throw (RuntimeException) cause;
        } else {
            throw new IllegalArgumentException(e.getCause());
        }
    }


    /**
     * 循环向上转型, 获取对象的Method
     * @param object :         类对象
     * @param methodName :     类方法
     * @param parameterTypes : 方法参数类型
     * @return 父类中的方法对象
     */
    public static Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes){
        for(Class<?> clazz = object.getClass(); clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                return getMethod(clazz,methodName,parameterTypes);
            } catch (Exception e) {
                //LOGGER.info("当前类："+ clazz + ", 未找到指定的方法："+methodName + ",继续查找父类");
            }
        }
        LOGGER.debug("当前类以及全部父类："+ object.getClass() + ", 未找到指定的方法："+methodName);
        return null;
    }

    /**
     * 循环向上转型, 获取对象的Field
     *
     * @param object :    类对象
     * @param fieldName : 属性名
     * @return 属性对象
     */

    public static Field getDeclaredField(Object object, String fieldName){
        Field field = null ;
        Class<?> clazz = object.getClass() ;
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (Exception e) {
                LOGGER.debug("当前类："+ clazz + ", 未找到指定的属性："+fieldName);
            }
        }
        return field;
    }

    /**
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
     * @param object :    类对象
     * @param fieldName : 属性名
     * @param value :     设置的值
     */
    public static void setFieldValue(Object object, String fieldName, Object value){
        try {
            Field field = getDeclaredField(object, fieldName) ;
            if(field != null){
                field.setAccessible(true) ;
                field.set(object, value) ;
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("设置属性的的值失败, 参数异常：",e);
        } catch (IllegalAccessException e) {
            LOGGER.error("设置属性的的值失败, 访问异常：",e);
        }

    }

    /**
     * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
     * @param object :    类对象
     * @param fieldName : 属性名
     * @return :          设置的值
     */
    public static <T> T getFieldValue(Object object, String fieldName){
        try {
            Field field = getDeclaredField(object, fieldName) ;
            if(field != null){
                field.setAccessible(true) ;
                return (T)field.get(object) ;
            }
        } catch(Exception e) {
            LOGGER.error("获得属性的的值失败, 异常：",e);
        }
        return null;
    }

    /**
     * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
     * @param object :    类对象
     * @param fieldName : 属性名
     * @return            设置的值
     */
    public static <T> T getFieldValueByMethod(Object object, String fieldName){
          String methodName = getGetPropertyMethod(fieldName);
          return  (T)callMethod(object,methodName,null,null);
    }

    /**
     * 获得ClassLoader
     *
     * @param jarFile
     * @return
     */
    public static ClassLoader getClassLoadIncludeJar(File jarFile){
        URLClassLoader classLoader = null;
        try {
            classLoader = URLClassLoader.newInstance(new URL[]{jarFile.toURI().toURL()}, Thread.currentThread().getContextClassLoader());
            LOGGER.info("读取jar文件[name={}]", jarFile.getName());
        } catch (Exception e){
            LOGGER.error("获取ClassLoader异常,原因：", e);
        }
        return classLoader;
    }
}