package com.dangnian.springboot.entity.response.enums;

/**
 * @Author chun.yin
 **/

public enum ResultCode {

    SUCCESS(0, "成功"),
    FAIL(-1, "失败"),
    SYSTEM_ERROR(10000, "系统异常，请稍后重试"),
    /*10001~19999*/
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_NOT_COMPLATE(10003, "参数缺失"),
    PARAM_NOT_ILLEGAL(10004, "参数不合法"),
    /*20001~29999*/
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_HAS_EXISTED(20002, "用户已存在");


    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }


}

