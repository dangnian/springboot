package com.dangnian.springboot.common.response.advice;

import com.dangnian.springboot.common.response.annotation.IgnoreResponseResult;
import com.dangnian.springboot.entity.response.result.ErrorResult;
import com.dangnian.springboot.entity.response.result.Result;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author chun.yin
 **/
@RestControllerAdvice(basePackages = "com.dangnian.springboot.web",
        basePackageClasses = GlobalExceptionHandler.class)
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ResponseHandle implements ResponseBodyAdvice<Object> {

    /**
     * 有注解标记的方法需要advice特殊处理
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseResult.class)) {
            return false;
        }
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseResult.class)) {
            return false;
        }
        return true;
    }

    /**
     * 请求返回之前处理
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ErrorResult) {
            ErrorResult errorResult = (ErrorResult) o;
            return Result.failure(errorResult.getCode(), errorResult.getMessage());
        }
        return Result.success(o);
    }
}
