package com.dch.service.dating.dao.mongodb;

import com.dch.service.dating.common.constant.DBConstants;
import com.dch.service.dating.dao.mongodb.doc.UsersDocument;

public class UsersDAO extends MongoDAOSupport<UsersDocument> {
    @Override
    protected String collectionName() {
        return DBConstants.TALBE_NAME_USERS;
    }
}
