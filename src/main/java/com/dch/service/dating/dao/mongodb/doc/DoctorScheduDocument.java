package com.dch.service.dating.dao.mongodb.doc;

import lombok.Data;

import java.util.Date;

@Data
public class DoctorScheduDocument extends BasicMongoDocument {

    private String addr;    // 放号地址

    private Date stime; // 放号开始时间

    private Date etime; // 放号结束时间

    private String doctorId;    // 放号医生（用户）id

}
