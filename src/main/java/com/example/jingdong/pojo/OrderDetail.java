package com.example.jingdong.pojo;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table( name = "order_detail" )
@Proxy(lazy = false)
public class OrderDetail {

    @Id
    private String detailId;
    private String orderNumber;
    private Integer productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String imgUrl;

}
