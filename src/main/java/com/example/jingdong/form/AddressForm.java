package com.example.jingdong.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddressForm {

    private Integer id;

    @NotNull( message = "所属用户id不可为空")
    private Integer userId;

    @NotNull( message = "收货人姓名不可为空")
    @Size(min = 2,max = 10,message = "收货人姓名格式不正确")
    private String consigneeName;

    @NotNull( message = "电话不可为空")
    @Size(min = 11,max = 11,message = "电话号码格式不正确")
    private String tel;

    @NotNull( message = "所在地区不可为空")
    private String area;

    @NotNull( message = "详细地址不可为空")
    private String detailedAddress;

    private Integer isDefault;

}
