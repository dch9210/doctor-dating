package com.dch.service.dating.common.exception;

public enum ErrorMsg {
    DB_SAVE_NULL(1, "DB save data cannot be null."),
    DB_PARAM_INVALID(2, "数据库执行参数校验失败."),
    API_INVALID_QUERY(3, "请求参数错误."),

    USERS_EXISTS(4, "该手机号已被注册."),
    TEL_INVALID(5, "手机号格式不正确."),
    NONE_USERS(5, "该用户不存在."),
    ;
    private int code;
    private String msg;

    ErrorMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
