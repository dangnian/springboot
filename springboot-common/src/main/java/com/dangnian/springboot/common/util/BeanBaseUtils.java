package com.dangnian.springboot.common.util;


import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * bean util，封装使用springframework BeanUtils
 * @author: jie.deng
 * @time: 2018年7月24日 上午9:57:58
 */
public class BeanBaseUtils {

	private static Map<String, BeanCopier> beanCopierMap = new HashMap<>();

	private BeanBaseUtils(){}

	public static void copyBean(Object source, Object target) {
		copyBean(source, target, false);
	}

	public static void copyBean(Object source, Object target, boolean forceCast) {

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null) {
				Class<?> paramType = writeMethod.getParameterTypes()[0];
				PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							if (forceCast) {
								if (null == value && paramType.isPrimitive()) {
									continue;	// 忽略null赋值给基本类型
								} else if (null != value){
									value = cast(value, paramType);
								}
							}
							if (ClassUtils.isAssignable(paramType, value == null ? readMethod.getReturnType() : value.getClass())) {
								writeMethod.invoke(target, value);
							}
						} catch (Exception ex) {
							throw new RuntimeException(
									"Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}

				}
			}
		}

	}

	private static Object cast(Object value, Class<?> returnType) {
		if (value != null) {
			if (value.getClass().isAssignableFrom(BigDecimal.class) && returnType == String.class) {
				return toPlainString((BigDecimal) value);
			} else if (value.getClass().isAssignableFrom(String.class) && returnType == BigDecimal.class) {
				return "".equals(value) ? null : new BigDecimal((String) value);
			}
		}
		return value;
	}

    /**
     * 将BigDecimal转化为字符串，返回不带指数字段的字符串表示形式，并去掉小数点后不必要的0
     * @param num
     * @return
     */
    public static String toPlainString(BigDecimal num) {
        if (null == num) {
            return "";
        }
        String str = num.toPlainString();
        if (str.indexOf(".") > 0) {
            // 正则表达
            str = str.replaceAll("0+$", "");// 去掉后面无用的零
            str = str.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
        }
        return str;
    }
    
	public static <T> T copyBean(Object source, Class<T> targetClass) {
		return copyBean(source, targetClass, false);
	}

	public static <T> T copyBean(Object source, Class<T> targetClass, boolean forceCast) {
		T target = null;
		try {
			target = targetClass.newInstance();
			copyBean(source, target, forceCast);
		} catch (Exception e) {
			throw new RuntimeException("拷贝对象异常", e);
		}
		return target;
	}

	public static <T> List<T> copyBeanList(List<?> dataList, Class<T> clazz) {
		return copyBeanList(dataList, clazz, false);
	}

	public static <T> List<T> copyBeanList(List<?> dataList, Class<T> clazz, boolean forceCast) {
		List<T> newList = new ArrayList<T>();
		try {
            if (dataList == null || dataList.size() == 0) {
                return newList;
            }
			for (Object source : dataList) {
				T target = clazz.newInstance();
				copyBean(source, target, forceCast);
				newList.add(target);
			}
		} catch (Exception e) {
			throw new RuntimeException("bean list 转换异常", e);
		}
		return newList;
	}

	/**
	 * Copy.
	 *
	 * @param source the source
	 * @param target the target
	 */
	public static void copy(Object source, Object target) {
		String beanKey = generateKey(source.getClass(), target.getClass());
		BeanCopier copier;
		if (!beanCopierMap.containsKey(beanKey)) {
			copier = BeanCopier.create(source.getClass(), target.getClass(), false);
			beanCopierMap.put(beanKey, copier);
		} else {
			copier = beanCopierMap.get(beanKey);
		}
		copier.copy(source, target, null);
	}

	/**
	 * Copy list list.
	 *
	 * @param <S>    the type parameter
	 * @param <T>    the type parameter
	 * @param source the source
	 * @param cls    the cls
	 * @return the list
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public static <S, T> List<T> copyList(List<S> source, Class<T> cls)
			throws InstantiationException, IllegalAccessException {
		List<T> target = new ArrayList<>();
		if (null != source && source.size() > 0) {
			for (S s : source) {
				T t = cls.newInstance();
				copy(s, t);
				target.add(t);
			}
		}
		return target;
	}

	/**
	 * Generate key string.
	 *
	 * @param classSource the class source
	 * @param classTarget the class target
	 * @return the string
	 */
	private static String generateKey(Class<?> classSource, Class<?> classTarget) {
		return classSource.toString() + classTarget.toString();
	}

	/**
	 * 将对象装换为map
	 *
	 * @param bean
	 * @return
	 */
	public static <T> Map<String, Object> beanToMap(T bean) {
		Map<String, Object> map =  new HashMap<>();
		if (bean != null) {
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				if(ObjectBaseUtils.isNotEmpty(beanMap.get(key))){
					map.put(key + "",beanMap.get(key));
				}
			}
		}
		return map;
	}
}
