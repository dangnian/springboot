package com.dangnian.springboot.common.response.enums;

/**
 * @Author chun.yin
 **/

public enum EResultCode {

    /*通用成功和失败*/
    SUCCESS("00000", "成功"),


    /*10001~19999 系统类异常*/
    SYSTEM_ERROR("10001", "系统异常,请稍后重试"),

    /*20001~29999 参数类异常*/
    PARAM_IS_INVALID("20001", "参数无效,详情："),
    PARAM_IS_BLANK("20002", "参数为空,详情："),
    PARAM_NOT_COMPLETE("20003", "参数缺失,详情："),
    PARAM_NOT_ILLEGAL("20004", "参数不合法,详情："),

    /*30001~39999 业务类异常*/
    DATA_QUERY_ERROR("30001", "数据查询异常,详情："),
    DATA_INSERT_ERROR("30002", "数据新增异常,详情："),
    DATA_UPDATE_ERROR("30002", "数据更新异常,详情："),
    DATA_EXPORT_ERROR("30003", "数据导出异常,详情："),
    STATUS_NOT_AVAILABLE("30004", "数据状态异常,详情："),
    FILE_UPLOAD_ERROR("30005", "文件上传异常,详情："),

    FAILURE("99999", "失败");

    private String code;

    private String message;

    EResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }



}

