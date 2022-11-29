package com.example.jingdong.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//用于更新店铺管理员数据
@Data
public class SellerAddForm {

    @NotNull(message = "用户名不可为空")
    @Size(min = 6,max = 18,message = "用户名长度必须在6-18之间")
    private String username;

    @NotNull(message = "密码不可为空")
    @Size(min = 6,max = 18,message = "用户名长度必须在6-18之间")
    private String password;

    @NotNull(message = "重复密码不可为空")
    @Size(min = 6,max = 18,message = "用户名长度必须在6-18之间")
    private String rePassword;

    @NotNull(message = "管理员真实姓名不可为空")
    private String realName;

    @NotNull(message = "管理员权限等级不可为空")
    private Integer privilege;

    @NotNull(message = "管理员联系邮箱不可为空")
    private String email;

}
