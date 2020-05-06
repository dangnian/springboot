package com.dangnian.springboot.entity.response.result;

import com.dangnian.springboot.entity.response.enums.ResultCode;

import java.io.Serializable;

/**
 * @Author chun.yin
 **/

public class Result<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态码描述
     */
    private String message;

    /**
     * 额外数据信息
     */
    private T data;

    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setResultCode(ResultCode.FAIL);
        return result;
    }

    public static Result fail(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result fail(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

}
