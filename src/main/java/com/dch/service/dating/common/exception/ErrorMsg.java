package com.dch.service.dating.common.exception;

public enum ErrorMsg {
    DB_SAVE_NULL(1, "DB save data cannot be null."),
    DB_PARAM_INVALID(2, "数据库执行参数校验失败."),
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
