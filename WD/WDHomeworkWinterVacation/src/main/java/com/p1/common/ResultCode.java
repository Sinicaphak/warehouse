package com.p1.common;


/**
 * 自定义状态码
 * */
public enum ResultCode {

    /**
     * 成功
     * */
    SUCCESS(200,"success"),

    /**
     * 失败：资源不存在
     * */
    NOT_FOUND(404,"not found"),

    /**
     * 失败：token异常
     * */
    TOKEN_BAD(403,"token bad");

    /**
     * 状态码
     * */
    private int code;

    /**
     * 信息
     * */
    private String message;


    ResultCode(int code,String message) {
        this.code = code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
