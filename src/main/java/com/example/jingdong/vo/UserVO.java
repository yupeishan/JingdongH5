package com.example.jingdong.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserVO {

    private Integer id;
    private String username;
    private String openId;
    private String nickname;
    private String headImgUrl;
    private String tel;
    private String headPortrait;
    private BigDecimal money;
    private Integer coupons;
    private BigDecimal goldCoin;
    private BigDecimal iou;
    private Integer state;
}
