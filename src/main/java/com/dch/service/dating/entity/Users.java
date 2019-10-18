package com.dch.service.dating.entity;

import com.dch.service.dating.dao.mongodb.enums.UsersType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class Users extends BaseModel{

    @ApiModelProperty(value = "电话号码")
    private String tel;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private int age;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("用户类型")
    private UsersType type;

}
