package com.dch.service.dating.common.util;

import com.dch.service.dating.common.exception.ErrorMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

@Slf4j
public class AssertHelper {

    public static final boolean isEmpty(Object obj) {
        return obj == null ? true : isEmpty(obj.toString());
    }

    public static final boolean notEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 判断字符串是否为空(null和 空白字符组成的字符串都算空)
     *
     * @param str 入参字符串
     * @return true/false
     */
    public static final boolean isEmpty(String str) {
        return str == null ? true : str.trim().length() == 0;
    }

    public static final boolean notEmpty(String str) {
        return !isEmpty(str);
    }

    public static final boolean isEmpty(Collection collection) {
        return collection == null ? true : collection.size() == 0;
    }

    public static final boolean notEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static final boolean isEmpty(Map map) {
        return map == null ? true : map.size() == 0;
    }

    public static final boolean notEmpty(Map map) {
        return !isEmpty(map);
    }

    public static final boolean isEmpty(Object[] objs) {
        return objs == null ? true : objs.length == 0;
    }

    public static final boolean notEmpty(Object[] objs) {
        return !isEmpty(objs);
    }

    public static final boolean isAllEmpty(String... strs) {
        for (String str : strs) {
            if (notEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isAllEmpty(Object... objs) {
        for (Object obj : objs) {
            if (notEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isAllNotEmpty(String... strs) {
        for (String str : strs) {
            if (isEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isNotAllEmpty(String... strs) {
        for (String str : strs) {
            if (notEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isAnyoneEmpty(Object... objs) {
        for (Object obj : objs) {
            if (isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isEquals(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 != null && obj2 != null) {
            return obj1.equals(obj2);
        }
        return false;
    }

    /**
     * 对象不为空
     *
     * @param obj
     */
    public static void assertNotEmpty(Object obj) {
        if (isEmpty(obj)) {
            log.error("AssertHelper assertNotEmpty, but obj is empty");
            Exceptions.throwss(ErrorMsg.DB_PARAM_INVALID);
        }
    }

    /**
     * <br>断言：入参字符串不为空
     *
     * @param str 要判断的字符串
     * @param msg 日志里会打印出来，但是不会显示在客户端
     * @author YellowTail
     * @since 2018-12-10
     */
    public static void assertNotEmpty(String str, String msg) {
        if (StringUtils.isEmpty(str)) {
            log.error("AssertHelper assertNotEmpty, but string is empty, {}", msg);
            Exceptions.throwss(ErrorMsg.DB_PARAM_INVALID);
        }
    }

    public static void assertNotEmpty(Collection<?> coll, String msg) {
        if (CollectionUtils.isEmpty(coll)) {
            log.error("AssertHelper assertNotEmpty, but collection is empty, {}", msg);
            Exceptions.throwss(ErrorMsg.DB_PARAM_INVALID);
        }
    }

    public static void assertNotNull(Object obj, String msg) {
        if (null == obj) {
            log.error("AssertHelper assertNotNull, but obj is empty, {}", msg);
            Exceptions.throwss(ErrorMsg.DB_PARAM_INVALID);
        }
    }


}
