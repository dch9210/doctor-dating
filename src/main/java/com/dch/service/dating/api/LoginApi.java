package com.dch.service.dating.api;

import com.dch.service.dating.common.ApiResult;
import com.dch.service.dating.common.exception.ErrorMsg;
import com.dch.service.dating.common.util.AssertHelper;
import com.dch.service.dating.common.util.Exceptions;
import com.dch.service.dating.entity.Users;
import com.dch.service.dating.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(value = "/sign", tags = {"sign"})
@RequestMapping("/sign")
@RestController
public class LoginApi {

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "根据电话号码获取用户信息", notes = "测试数据")
    @GetMapping("/users/test")
    public ApiResult getUsersTest(String tel) {
        log.debug("LoginApi getUsersTest param tel {}", tel);
        Users users = usersService.getUserByTelForTest(tel);
        return ApiResult.ok(users);
    }

    @ApiOperation(value = "注册新用户", notes = "必填手机号+密码")
    @PostMapping("/up")
    public ApiResult registUser(@RequestBody Users users, HttpServletRequest request, HttpSession session) {
        Users result = usersService.registNewUser(users);
        session.setAttribute("_SIGN_USERS", result);
        return ApiResult.ok(result);
    }

    @ApiOperation(value = "登录", notes = "必填手机号+密码")
    @PostMapping("/in")
    public ApiResult login(@RequestBody Users users, HttpSession session) {
        if(AssertHelper.isEmpty(users))
            Exceptions.throwss(ErrorMsg.API_INVALID_QUERY);
        users = usersService.getUsersByTelAndPwd(users.getTel(), users.getPwd());
        if(AssertHelper.isEmpty(users))
            Exceptions.throwss(ErrorMsg.NONE_USERS);
        session.setAttribute("_SIGN_USERS", users);
        return ApiResult.ok(users);
    }

}
