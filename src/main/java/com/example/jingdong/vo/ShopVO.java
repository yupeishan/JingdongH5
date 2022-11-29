package com.example.jingdong.vo;

import com.example.jingdong.enums.StateEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShopVO {

    private Integer id;
    private String name;
    private String cateName;
    private Integer sales;
    private Integer expressLimit;
    private BigDecimal expressPrice;
    private String discount;
    private Integer state = StateEnum.STATE_YES.getCode();
    private Integer recommend;



}
