package com.dch.service.dating.common.exception;

public enum ErrorMsg {
    DB_SAVE_NULL(1, "DB save data cannot be null."),
    DB_PARAM_INVALID(2, "数据库执行参数校验失败."),
    API_INVALID_QUERY(3, "请求参数错误."),
    NONE_LOGIN(10, "无法获取到用户信息，请重新登录."),

    USERS_EXISTS(4, "该手机号已被注册."),
    TEL_INVALID(5, "手机号格式不正确."),
    NONE_USERS(5, "用户名或密码输入错误."),

    DOCTOR_SCHEDU_CREATE_NONE_ADDR(6, "放号地址不能为空"),
    DOCTOR_SCHEDU_CREATE_NONE_STIME(7, "放号起始时间不能为空"),
    DOCTOR_SCHEDU_CREATE_NONE_ETIME(8, "放号结束时间不能为空"),
    DOCTOR_SCHEDU_CREATE_NOT_DOCTOR(9, "非医生账户不能进行放号操作"),
    NOT_DOCTOR(11, "非医生账号，请重新登录"),
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
