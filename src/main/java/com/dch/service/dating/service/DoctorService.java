package com.dch.service.dating.service;

import com.dch.service.dating.common.exception.ErrorMsg;
import com.dch.service.dating.common.util.AssertHelper;
import com.dch.service.dating.common.util.Exceptions;
import com.dch.service.dating.dao.mongodb.DoctorScheduDAO;
import com.dch.service.dating.dao.mongodb.UsersDAO;
import com.dch.service.dating.dao.mongodb.doc.DoctorScheduDocument;
import com.dch.service.dating.dao.mongodb.doc.UsersDocument;
import com.dch.service.dating.dao.mongodb.enums.UsersType;
import com.dch.service.dating.entity.GetDoctorScheduListReq;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class DoctorService {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private DoctorScheduDAO doctorScheduDAO;

    public DoctorScheduDocument createNewDoctorSchedu(DoctorScheduDocument doc) {
        if (AssertHelper.isEmpty(doc))
            Exceptions.throwss(ErrorMsg.API_INVALID_QUERY);
        if (AssertHelper.isEmpty(doc.getAddr()))
            Exceptions.throwss(ErrorMsg.DOCTOR_SCHEDU_CREATE_NONE_ADDR);
        if (AssertHelper.isEmpty(doc.getStime()))
            Exceptions.throwss(ErrorMsg.DOCTOR_SCHEDU_CREATE_NONE_STIME);
        if (AssertHelper.isEmpty(doc.getEtime()))
            Exceptions.throwss(ErrorMsg.DOCTOR_SCHEDU_CREATE_NONE_ETIME);
        if (AssertHelper.isEmpty(doc.getDoctorId()))
            Exceptions.throwss(ErrorMsg.DOCTOR_SCHEDU_CREATE_NOT_DOCTOR);
        UsersDocument user = usersDAO.getById(doc.getDoctorId());
        if (user.getType() != UsersType.DOCTOR)
            Exceptions.throwss(ErrorMsg.DOCTOR_SCHEDU_CREATE_NOT_DOCTOR);
        // TODO: 校验是否重复号

        String _id = doctorScheduDAO.save(doc);
        doc.set_id(_id);
        return doc;
    }

    public List<DoctorScheduDocument> getDoctorScheduList(String doctorId, GetDoctorScheduListReq body) {
        UsersDocument user = usersDAO.getById(doctorId);
        if (AssertHelper.isEmpty(user) || user.getType() != UsersType.DOCTOR) {
            Exceptions.throwss(ErrorMsg.NOT_DOCTOR);
        }
        DateTime stime = new DateTime(body.getQueryDate()).withTimeAtStartOfDay();
        DateTime etime = stime.plusDays(1);
        return doctorScheduDAO.getListByDoctorId(doctorId, stime.toDate(), etime.toDate());
    }

    public void deleteDoctorSchedu(String id) {
        doctorScheduDAO.removeByDocId(id);
    }

}
