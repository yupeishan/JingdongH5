package com.example.jingdong.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PasswordForm {

    @NotNull(message = "用户id不可为空")
    private Integer Id;

    private String oldPassword;

    @NotNull(message = "新密码不可为空")
    @Size(min = 6,max = 18,message = "新密码长度必须在6-18之间")
    private String password;

    @NotNull(message = "重复密码不可为空")
    @Size(min = 6,max = 18,message = "重复密码长度必须在6-18之间")
    private String rePassword;

}
