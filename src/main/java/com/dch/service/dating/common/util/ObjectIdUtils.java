package com.dch.service.dating.common.util;

import org.bson.types.ObjectId;
import org.springframework.util.StringUtils;

public class ObjectIdUtils {

    public ObjectIdUtils() {
    }

    public static boolean isValid(String hexString) {
        return StringUtils.isEmpty(hexString) ? false : ObjectId.isValid(hexString);
    }

    public static boolean isNotValid(String hexString) {
        return !isValid(hexString);
    }
}
