package com.dch.service.dating.common.exception;

import lombok.Data;

@Data
public class BizExcpetion extends RuntimeException {
    private int code;
    private String msg;

    public BizExcpetion(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
