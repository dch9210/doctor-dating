package com.dch.service.dating.common.util;

import com.dch.service.dating.common.exception.BizExcpetion;
import com.dch.service.dating.common.exception.ErrorMsg;

public class Exceptions {

    public static void throwss(int code, String msg) {
        throw new BizExcpetion(code, msg);
    }

    public static void throwss(ErrorMsg errorMsg) {
        throwss(errorMsg.getCode(), errorMsg.getMsg());
    }

}
