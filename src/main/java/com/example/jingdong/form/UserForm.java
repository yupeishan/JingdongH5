package com.example.jingdong.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserForm {

    @NotNull(message = "用户id不可为空")
    private Integer id;

    @NotNull(message = "用户昵称不可为空")
    @Size(min = 2,max = 12,message = "昵称长度应在2-12之间")
    private String nickname;

    @NotNull(message = "用户手机号码不可为空")
    @Size(min = 11,max = 11,message = "手机号码格式错误")
    private String tel;

}
