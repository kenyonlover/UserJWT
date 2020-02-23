package com.example.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class User {

    @ApiModelProperty(value = "用户id")
    Integer id;
    @ApiModelProperty(value = "用户名")
    String username;
    @ApiModelProperty(value = "用户密码")
    String password;
}
