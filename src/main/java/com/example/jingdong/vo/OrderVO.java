package com.example.jingdong.vo;

import com.example.jingdong.enums.OrderStateEnum;
import com.example.jingdong.enums.PayStateEnum;
import com.example.jingdong.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderVO {

    private String orderNumber;
    private String transactionNumber;
    private String consigneeName;
    private String tel;
    private String address;
    private String courierNumber;
    private BigDecimal orderAmount;
    private Integer payState = PayStateEnum.PAY_NOT.getCode();
    private Integer orderState = OrderStateEnum.ORDER_WAIT_CONFIRM.getCode();
    private String payTime;
    private String createTime;
    private String updateTime;


}
