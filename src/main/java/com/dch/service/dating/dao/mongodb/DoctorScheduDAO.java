package com.dch.service.dating.dao.mongodb;


import com.dch.service.dating.common.constant.DBConstants;
import com.dch.service.dating.dao.mongodb.doc.DoctorScheduDocument;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DoctorScheduDAO extends MongoDAOSupport<DoctorScheduDocument> {

    @Override
    protected String collectionName() {
        return DBConstants.TALBE_NAME_DOCTOR_SCHEDU;
    }

    public List<DoctorScheduDocument> getListByDoctorId(String doctorId, Date stime, Date etime) {
        Query query = new Query(Criteria.where("doctorId").is(doctorId).and("stime").gte(stime).lte(etime).and("etime").gte(stime).lte(etime));
        return getList(query);
    }

}
