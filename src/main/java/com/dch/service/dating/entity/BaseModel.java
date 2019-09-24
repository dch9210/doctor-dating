package com.dch.service.dating.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class BaseModel {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "创建日期")
    private Date createtime;

    @ApiModelProperty(value = "修改日期")
    private Date modifyTime;

    @ApiModelProperty(value = "删除标记")
    private Integer del_flag;

}
