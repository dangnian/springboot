package com.dangnian.springboot.common.response.exception;

import com.dangnian.springboot.entity.response.enums.ResultCode;

/**
 * @Author chun.yin
 **/

public class BusinessException extends RuntimeException{

    protected Integer exceptionCode;

    protected String exceptionMessage;

    public BusinessException(ResultCode resultCode, String detailMessage) {
        this(resultCode.code(), resultCode.message() + "，详情：" + detailMessage);
    }

    public BusinessException(ResultCode resultCode) {
       this(resultCode.code(), resultCode.message());
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.exceptionCode = code;
        this.exceptionMessage = message;
    }

    public Integer getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(Integer exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
