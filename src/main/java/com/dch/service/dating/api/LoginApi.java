package com.dch.service.dating.api;

import com.dch.service.dating.entity.Users;
import com.dch.service.dating.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api
@RestController
public class LoginApi {

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "根据电话号码获取用户信息", notes = "测试数据")
    @GetMapping("/users/test")
    public Users getUsersTest(String tel) {
        log.debug("LoginApi getUsersTest param tel {}", tel);
        Users users = usersService.getUserByTelForTest(tel);
        return users;
    }

}
