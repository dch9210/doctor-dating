package com.dch.service.dating.dao.mongodb.doc;

import com.dch.service.dating.dao.mongodb.enums.UsersType;
import lombok.Data;

@Data
public class UsersDocument extends BasicMongoDocument{

    // 手机号
    private String tel;

    // 密码
    private String pwd;

    // 用户类型
    private UsersType type;

    // 姓名
    private String name;

    // 性别
    private String gender;

}
