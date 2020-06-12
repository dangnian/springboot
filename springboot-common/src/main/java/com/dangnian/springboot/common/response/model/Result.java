package com.dangnian.springboot.common.response.model;


import com.dangnian.springboot.common.response.enums.EResultCode;

import java.io.Serializable;

/**
 * @Author chun.yin
 **/

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 2407950862125912336L;

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

    public void setResultCode(EResultCode EResultCode) {
        this.code = EResultCode.code();
        this.message = EResultCode.message();
    }

}
