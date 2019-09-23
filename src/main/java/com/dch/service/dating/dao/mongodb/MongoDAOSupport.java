package com.dch.service.dating.dao.mongodb;

import com.dch.service.dating.common.constant.DBConstants;
import com.dch.service.dating.common.exception.ErrorMsg;
import com.dch.service.dating.common.util.AssertHelper;
import com.dch.service.dating.common.util.Exceptions;
import com.dch.service.dating.common.util.GenericsUtils;
import com.dch.service.dating.common.util.ObjectIdUtils;
import com.dch.service.dating.dao.mongodb.doc.BasicMongoDocument;
import com.mongodb.client.result.UpdateResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Data
@Slf4j
public abstract class MongoDAOSupport<T extends BasicMongoDocument> {

    @Autowired
    private MongoTemplate mongoTemplate;

    protected Class<T> entityClass;

    public MongoDAOSupport() {
        entityClass = GenericsUtils.getSuperClassGenricType(getClass());
    }

    protected abstract String collectionName();

    protected T get(Query query){
        return getMongoTemplate().findOne(query, entityClass, collectionName());
    }

    public List<T> getByIds(Collection<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        List<ObjectId> objectIds = idsToObjectIds(ids);
        if (CollectionUtils.isEmpty(objectIds)) {
            return new ArrayList<>();
        }

        Query query = new Query(Criteria.where("_id").in(objectIds));
        return getList(query);
    }

    public List<ObjectId> idsToObjectIds(Collection<String> ids) {
        List<ObjectId> objectIds = ids.stream()
                .filter(k -> ObjectIdUtils.isValid(k))        //格式需要合法，前端传过来的数据经常 不合法
                .map(id -> new ObjectId(id))
                .collect(Collectors.toList());
        return objectIds;
    }

    public List<T> getList(Query query) {
        return this.getList(query, entityClass, collectionName(), 0, 0);
    }

    protected List<T> getList(Query query, int limit, int skip) {
        return this.getList(query, entityClass, collectionName(), limit, skip);
    }

    protected List<T> getList(Query query, int limit) {
        return this.getList(query, entityClass, collectionName(), limit, 0);
    }

    protected List<T> getListWithSkip(Query query, int skip) {
        return this.getList(query, entityClass, collectionName(), 0, skip);
    }

    protected <K> List<K> getList(Query query, Class<K> clazz) {
        return this.getList(query, clazz, collectionName(), 0, 0);
    }
    protected <K> List<K> getList(Query query, Class<K> clazz, int limit) {
        return this.getList(query, clazz, collectionName(), limit, 0);
    }
    protected <K> List<K> getListWithSkip(Query query, Class<K> clazz, int skip) {
        return this.getList(query, clazz, collectionName(), 0, skip);
    }

    protected <K> List<K> getList(Query query, Class<K> clazz, String collectionName, int limit, int skip) {
        if(limit > 0){
            query.limit(limit);
        }

        if(skip > 0){
            query.skip(skip);
        }

        return getMongoTemplate().find(query, clazz, collectionName);
    }

    public String save(T t) {
        assertFieldNotNull(t);
        Date now = new Date();
        if (t.getCreateTime() == null) {
            t.setCreateTime(now);
        }
        if (t.getModifyTime() == null) {
            t.setModifyTime(now);
        }
        mongoTemplate.insert(t, collectionName());
        return t.get_id();
    }

    public String saveOnly(T t) {
        assertFieldNotNull(t);
        mongoTemplate.insert(t, collectionName());
        return t.get_id();
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = Update.update("del_flag_inner", DBConstants.DEL_FLAG_TRUE);
        update(query, update);
    }

    public void update(T t) {
        t.setModifyTime(new Date());
        updateDoc(t);
    }

    public void updateDoc(T t) {
        AssertHelper.assertNotEmpty(t.get_id());
        assertFieldNotNull(t);
        mongoTemplate.save(t, collectionName());
    }

    /**
     * 根据id批量更新字段
     *
     * @param id
     * @param paramMap 这里要hashMap
     */
    public void multiUpdateFieldById(String id, HashMap<String, Object> paramMap) {

        if (ObjectIdUtils.isNotValid(id) || AssertHelper.isEmpty(paramMap)) {
            return;
        }
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        Update update = new Update();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            update = update.set(entry.getKey(), paramMap.get(entry.getKey()));
        }
        update(query, update);
    }

    public boolean removeById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return remove(query);
    }

    public boolean removeByDocId(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return remove(query);
    }

    protected boolean remove(Query query) {
        mongoTemplate.remove(query, collectionName());
        return true;
    }

    /**
     * 根据自定义query来批量更新字段
     *
     * @param query
     * @param paramMap
     */
    public void multiUpdateFieldByQuery(Query query, HashMap<String, Object> paramMap) {

        if (AssertHelper.isEmpty(paramMap)) {
            Exceptions.throwss(ErrorMsg.DB_PARAM_INVALID);
        }
        Update update = new Update();
        for (String key : paramMap.keySet()) {
            update = update.set(key, paramMap.get(key));
        }
        update(query, update);
    }

    public void update(Query query, Update update) {
        if (AssertHelper.notEmpty(update.getUpdateObject())) {
            update.set("modifyTime", new Date());
            mongoTemplate.updateFirst(query, update, collectionName());
        }
    }

    /**
     * <br>通过id 列表，批量软删除记录
     *
     * @param ids
     * @since 2018-09-25
     */
    public void batchDeleteByIds(List<String> ids) {
        Query query = new Query(Criteria.where("_id").in(ids));
        Update update = Update.update("del_flag_inner", DBConstants.DEL_FLAG_TRUE);
        updateMulti(query, update);
    }

    /**
     * 批量更新，设置修改时间
     *
     * @return
     */
    public UpdateResult updateMulti(Query query, Update update) {

        if (AssertHelper.notEmpty(update.getUpdateObject())) {
            update.set("modifyTime", new Date());
            return mongoTemplate.updateMulti(query, update, collectionName());
        }
        return null;
    }

    /**
     * 校验@NotNull注解的字段是否为null，是则报错
     */
    private void assertFieldNotNull(T t) {
        AssertHelper.assertNotEmpty(t);
//        Field[] fields = entityClass.getDeclaredFields();
//        for (Field field : fields) {
//            if (field.isAnnotationPresent(Notnull.class)) {
//                field.setAccessible(true);
//                try {
//                    AssertHelper.assertNotNull(field.get(t), entityClass.getSimpleName().concat(" ").concat(field.getName().concat("字段不能为null")));
//                } catch (IllegalAccessException e) {
//                    log.error(entityClass.getSimpleName().concat("assertFieldNotNull method throw IllegalAccessException"));
//                    e.printStackTrace();
//                }
//            }
//        }
    }

}
