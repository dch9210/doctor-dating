package com.dch.service.dating.service;

import com.dch.service.dating.dao.mongodb.UsersDAO;
import com.dch.service.dating.entity.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersDAO usersDAO;

    public Users getUserByTelForTest(String tel) {
        Users users = new Users();
        users.setTel("18688739281");
        users.setPwd("dncnha9210");
        return users;
    }

}
