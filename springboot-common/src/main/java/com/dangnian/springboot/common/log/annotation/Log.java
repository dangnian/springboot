package com.dangnian.springboot.common.log.annotation;


import com.dangnian.springboot.common.log.enums.EOperateType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author chun.yin
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    String module() default "";

    /**
     * 描述
     */
    String describe() default "";

    /**
     * 操作类型
     */
    EOperateType operateType() default EOperateType.DEFAULT;


}
