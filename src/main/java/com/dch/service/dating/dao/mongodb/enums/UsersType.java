package com.dch.service.dating.dao.mongodb.enums;

/**
 * 用户类型枚举.
 */
public enum UsersType {
    DOCTOR(1, "医生"),
    PATIENT(2, "病人"),
    ;
    private int code;
    private String desc;

    UsersType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
