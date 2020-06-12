package com.dangnian.springboot.common.util;


import com.dangnian.springboot.common.response.enums.EResultCode;
import com.dangnian.springboot.common.response.model.Result;

/**
 * @Author chun.yin
 **/

public class ResultUtils {

    public static Result success() {
        Result result = new Result();
        result.setResultCode(EResultCode.SUCCESS);
        return result;
    }

    public static Result success(String message) {
        Result result = new Result();
        result.setCode(EResultCode.SUCCESS.code());
        result.setMessage(message);
        return result;
    }

    public static Result success(Object data) {
        Result result = ResultUtils.success();
        result.setData(data);
        return result;
    }

    public static Result failure(EResultCode eResultCode) {
        Result result = new Result();
        result.setResultCode(eResultCode);
        return result;
    }

    public static Result failure(EResultCode eResultCode, String detailMessage) {
        Result result = ResultUtils.failure(eResultCode);
        result.setData(detailMessage);
        return result;
    }

    public static Result failure(String code, String message, Object detailMessage) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(detailMessage);
        return result;
    }

    public static Result failure(String code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
