package com.dangnian.springboot.common.response.advice;

import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author chun.yin
 **/

@RestControllerAdvice(basePackages = "com.dangnian.springboot.web")
public class GlobalExceptionHandler {

   /* *//**
     * 服务器运行时异常
     *//*
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult handleThrowable(Throwable e) {
        ErrorResult errorResult = ErrorResult.failure(ResultCode.SYSTEM_ERROR, e);
        return errorResult;
    }

    @ExceptionHandler(value = BusinessException.class)
    public ErrorResult handleBusinessException(BusinessException e) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(e.getExceptionCode());
        errorResult.setMessage(e.getExceptionMessage());
        errorResult.setExceptionName(e.getClass().getName());
        return errorResult;
    }*/

}
