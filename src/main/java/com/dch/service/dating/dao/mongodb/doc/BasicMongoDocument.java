package com.dch.service.dating.dao.mongodb.doc;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class BasicMongoDocument {

    @Id
    private String _id;
    protected Date modifyTime;
    protected Date createTime;
    protected Integer del_flag;

}
