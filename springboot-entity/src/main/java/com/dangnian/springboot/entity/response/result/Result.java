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
    private String code;

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
        Result result = Result.success();
        result.setData(data);
        return result;
    }

    public static Result failure(ErrorResult errorResult) {
        Result result = new Result();
        result.setCode(errorResult.getCode());
        result.setMessage(errorResult.getMessage());
        return result;
    }

    public static Result failure(ErrorResult errorResult, Object data) {
        Result result = Result.failure(errorResult);
        result.setData(data);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
