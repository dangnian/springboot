package com.dangnian.springboot.common.response.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 协助处理方法有固定返回值但被全局异常特殊处理
 * @Author chun.yin
 * @Date 2020/5/28 16:02
 **/

@Component
public class ExceptionHandlerSupportInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
       /* GlobalExceptionHandler exceptionHandler = SpringUtil.getBean(GlobalExceptionHandler.class);
        if (EExceptionReturnObject.containReturnType(method.getReturnType())) {
            exceptionHandler.setExceptionReturnObjectTag(true);
            exceptionHandler.setExceptionReturnObject(EExceptionReturnObject.getExceptionReturnObjectByReturnType(method.getReturnType()));
        } else {
            exceptionHandler.setExceptionReturnObjectTag(false);
            exceptionHandler.setExceptionReturnObject(null);
        }*/
        return true;
    }

}
