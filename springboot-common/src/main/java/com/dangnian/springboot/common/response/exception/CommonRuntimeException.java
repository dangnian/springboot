package com.dangnian.springboot.common.response.exception;


import com.dangnian.springboot.common.response.enums.EResultCode;

/**
 * @Author chun.yin
 **/

public class CommonRuntimeException extends RuntimeException{

    protected String exceptionCode;

    protected String exceptionMessage;

    public CommonRuntimeException(String exceptionCode, String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionCode = exceptionCode;
        this.exceptionMessage  = exceptionMessage;
    }

    public CommonRuntimeException(String exceptionCode, String exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
        this.exceptionCode = exceptionCode;
        this.exceptionMessage  = exceptionMessage;
    }

    public CommonRuntimeException(EResultCode eResultCode) {
        super(eResultCode.message());
        this.exceptionCode = eResultCode.code();
        this.exceptionMessage = eResultCode.message();
    }

    public CommonRuntimeException(EResultCode eResultCode, Throwable cause) {
        super(eResultCode.message(), cause);
        this.exceptionCode = eResultCode.code();
        this.exceptionMessage = eResultCode.message();
    }

    public CommonRuntimeException(EResultCode eResultCode, String detailMessage) {
        super(eResultCode.message());
        this.exceptionCode = eResultCode.code();
        this.exceptionMessage = eResultCode.message() + detailMessage;
    }

    public CommonRuntimeException(EResultCode eResultCode, String detailMessage, Throwable e) {
        super(eResultCode.message(), e);
        this.exceptionCode = eResultCode.code();
        this.exceptionMessage = eResultCode.message() + detailMessage;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

}
