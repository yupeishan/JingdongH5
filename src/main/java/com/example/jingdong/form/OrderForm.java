package com.example.jingdong.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderForm {

    @Min(message = "用户id错误",value = 1)
    @NotNull(message = "购买人userId不可为空")
    private Integer userId;

    @NotNull(message = "收货人姓名不可为空")
    private String consigneeName;

    @NotNull(message = "收货人联系方式不可为空")
    private String tel;

    @NotNull(message = "收货人收货地址不可为空")
    private String address;

    @NotNull(message = "用户openid不可为空,请先绑定微信")
    private String userOpenId;

    @NotNull(message = "店铺id不可为空")
    private Integer shopId;

    @NotNull(message = "店铺名称不可为空")
    private String shopName;

    @NotNull(message = "购买商品不可为空")
    private String products;



}
