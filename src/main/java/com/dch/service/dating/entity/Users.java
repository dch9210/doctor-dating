package com.dch.service.dating.entity;

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

}
