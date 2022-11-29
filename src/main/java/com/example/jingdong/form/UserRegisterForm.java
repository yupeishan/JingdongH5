package com.example.jingdong.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegisterForm {

    @NotNull( message = "请输入用户名")
    @Size(min = 6,max = 18,message = "用户名长度必须在6-18之间")
    private String username;

    @NotNull( message = "请输入密码")
    @Size( min = 6 ,max = 18,message = "密码长度需要在6-18之间")
    private String password;

    @NotNull( message = "请确认密码")
    @Size( min = 6 ,max = 18,message = "重复密码长度需要在6-18之间")
    private String rePassword;

}
