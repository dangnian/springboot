package com.dangnian.springboot.common.response.advice;

import com.dangnian.springboot.common.response.annotation.ResponseResult;
import com.dangnian.springboot.common.util.GsonUtils;
import com.dangnian.springboot.entity.response.result.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author chun.yin
 **/
@ControllerAdvice
public class ResponseHandle implements ResponseBodyAdvice<Object> {

    /**
     * 有注解标记的方法需要advice特殊处理
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        ResponseResult responseResult = (ResponseResult) request.getAttribute(ResponseResult.class.getSimpleName());
        return responseResult == null ? false : true;
    }

    /**
     * 请求返回之前处理
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 返回页面地址特殊处理
        if (o instanceof String) {
            return GsonUtils.BeanToJson(o);
        }
        // 单条数据操作成功/失败
        if (o instanceof  Boolean) {
            if ((Boolean) o) {
                return Result.success();
            } else {
                return Result.fail();
            }
        }

        return Result.success(o);
    }
}
