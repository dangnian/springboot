package com.dangnian.springboot.entity.response.result;

import com.dangnian.springboot.entity.response.enums.ResultCode;

/**
 * @Author chun.yin
 **/

public class ErrorResult {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 异常详情-客户端可见
     */
    private String message;

    /**
     * 异常名称
     */
    private String exceptionName;

    public static ErrorResult failure(ResultCode resultCode, Throwable e) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(resultCode.code());
        errorResult.setMessage(resultCode.message());
        errorResult.setExceptionName(e.getClass().getName());
        return errorResult;
    }

    public static ErrorResult failure(ResultCode resultCode, Throwable e, String message) {
        ErrorResult errorResult = ErrorResult.failure(resultCode, e);
        errorResult.setMessage(message);
        return errorResult;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }
}
