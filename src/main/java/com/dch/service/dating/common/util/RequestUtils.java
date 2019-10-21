package com.dch.service.dating.common.util;

import com.dch.service.dating.common.exception.ErrorMsg;
import com.dch.service.dating.entity.Users;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static String checkUserId(HttpServletRequest request) {
        Users user = (Users) request.getSession().getAttribute("_SIGN_USERS");
        if (AssertHelper.isEmpty(user) || AssertHelper.isEmpty(user.getId()))
            Exceptions.throwss(ErrorMsg.NONE_LOGIN);
        return user.getId();
    }

}
