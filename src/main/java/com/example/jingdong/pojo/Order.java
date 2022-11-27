package com.example.jingdong.pojo;

import com.example.jingdong.enums.OrderStateEnum;
import com.example.jingdong.enums.PayStateEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table( name = "order_table" )
@Proxy(lazy = false)
@DynamicInsert
@DynamicUpdate
public class Order {

    @Id
    private String orderNumber;
    private String transactionNumber;
    private Integer userId;
    private String consigneeName;
    private String tel;
    private String address;
    private String courierNumber;
    private String userOpenId;
    private Integer shopId;
    private String shopName;
    private BigDecimal orderAmount;
    private Integer payState = PayStateEnum.PAY_NOT.getCode();
    private Integer orderState = OrderStateEnum.ORDER_WAIT_CONFIRM.getCode();
    private String payTime;
    private String createTime;
    private String updateTime;
}
