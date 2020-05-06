package com.dangnian.springboot.common.response.interceptor;

import com.dangnian.springboot.common.response.annotation.ResponseResult;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 定义拦截器
 * @Author chun.yin
 **/
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {

    /**
     * 请求拦截器前置处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if (handler instanceof  HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(ResponseResult.class.getSimpleName(), clazz.getAnnotation(ResponseResult.class));
            } else if (method.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(ResponseResult.class.getSimpleName(), method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}