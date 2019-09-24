package com.dch.service.dating.dao.mongodb;

import com.dch.service.dating.common.constant.DBConstants;
import com.dch.service.dating.common.util.AssertHelper;
import com.dch.service.dating.dao.mongodb.doc.UsersDocument;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class UsersDAO extends MongoDAOSupport<UsersDocument> {
    @Override
    protected String collectionName() {
        return DBConstants.TALBE_NAME_USERS;
    }

    public UsersDocument getUsers(String tel) {
        Query query = new Query(Criteria.where("tel").is(tel));
        return get(query);
    }

    public UsersDocument getUsers(String tel, String pwd) {
        Query query = new Query(Criteria.where("tel").is(tel).and("pwd").is(pwd));
        return get(query);
    }


}
