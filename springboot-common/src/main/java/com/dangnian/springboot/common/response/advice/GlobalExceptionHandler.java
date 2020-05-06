package com.dangnian.springboot.common.response.advice;

import com.dangnian.springboot.entity.response.enums.ResultCode;
import com.dangnian.springboot.entity.response.result.ErrorResult;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author chun.yin
 **/

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    /**
     * 服务器运行时异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult HandleThrowable(Throwable e) {
        ErrorResult errorResult = ErrorResult.fail(ResultCode.SYSTEM_ERROR, e);
        return errorResult;
    }

}
