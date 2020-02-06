package com.dch.service.dating.common;

import lombok.Data;

@Data
public class ApiResult {

    private String type;    // ok / error

    private int code;

    private String msg;

    private Object data;

    public static ApiResult error(int code, String msg) {
        ApiResult result = new ApiResult();
        result.setType("error");
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static ApiResult ok() {
        ApiResult result = new ApiResult();
        result.setType("ok");
        result.setCode(0);
        result.setMsg("ok");
        return result;
    }

    public static ApiResult ok(Object data) {
        ApiResult result = new ApiResult();
        result.setCode(0);
        result.setType("ok");
        result.setMsg("ok");
        result.setData(data);
        return result;
    }

}
