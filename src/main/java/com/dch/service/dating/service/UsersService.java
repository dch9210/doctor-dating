package com.dch.service.dating.service;

import com.dch.service.dating.common.exception.ErrorMsg;
import com.dch.service.dating.common.util.AssertHelper;
import com.dch.service.dating.common.util.Exceptions;
import com.dch.service.dating.dao.mongodb.UsersDAO;
import com.dch.service.dating.dao.mongodb.doc.UsersDocument;
import com.dch.service.dating.dao.mongodb.enums.UsersType;
import com.dch.service.dating.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersService {

    @Autowired
    private UsersDAO usersDAO;

    public Users getUserByTelForTest(String tel) {
        Users users = new Users();
//        users.setTel("18688739281");
//        users.setPwd("dncnha9210");
        return users;
    }

    /**
     * 注册用户.
     *
     * @param users
     * @return
     */
    public Users registNewUser(Users users) {
        log.debug("UserService registNewUser users {}", users);
        if (AssertHelper.isEmpty(users) || AssertHelper.isAnyoneEmpty(users.getTel(), users.getPwd()))
            Exceptions.throwss(ErrorMsg.API_INVALID_QUERY);
        if (!users.getTel().matches("^[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$"))
            Exceptions.throwss(ErrorMsg.TEL_INVALID);
        UsersDocument usersDocument = usersDAO.getUsers(users.getTel());
        if (AssertHelper.notEmpty(usersDocument))
            Exceptions.throwss(ErrorMsg.USERS_EXISTS);
        usersDocument = new UsersDocument();
        usersDocument.setTel(users.getTel());
        usersDocument.setPwd(users.getPwd());
        usersDocument.setName(users.getName());
        usersDocument.setGender(users.getGender());
        usersDocument.setType(UsersType.PATIENT);
        usersDAO.save(usersDocument);
        return genUsers(usersDocument);
    }

    public Users getUsersByTelAndPwd(String tel, String pwd) {
        UsersDocument usersDocument = usersDAO.getUsers(tel, pwd);
        return genUsers(usersDocument);
    }

    private Users genUsers(UsersDocument usersDocument) {
        if(AssertHelper.isEmpty(usersDocument))
            return null;
        Users users = new Users();
        users.setId(usersDocument.get_id());
        users.setCreatetime(usersDocument.getCreateTime());
        users.setModifyTime(usersDocument.getModifyTime());
        users.setTel(usersDocument.getTel());
        users.setPwd(usersDocument.getPwd());
        users.setName(usersDocument.getName());
        users.setTel(usersDocument.getTel());
        users.setType(usersDocument.getType());
        return users;
    }

}
