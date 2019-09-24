package com.dch.service.dating.common;

import lombok.Data;

@Data
public class ApiResult<T> {

    private int code;

    private String msg;

    private T data;

    public static ApiResult error(int code, String msg) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static ApiResult ok() {
        ApiResult result = new ApiResult();
        result.setCode(0);
        result.setMsg("ok");
        return result;
    }

    public static <T> ApiResult<T> ok(T data) {
        ApiResult result = new ApiResult();
        result.setCode(0);
        result.setMsg("ok");
        result.setData(data);
        return result;
    }

}
