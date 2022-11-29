package com.example.jingdong.pojo;


import com.example.jingdong.enums.StateEnum;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table( name = "shop" )
@Proxy( lazy = false )
public class Shop {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cateId;
    private String name;
    private Integer sales;
    private Integer expressLimit;
    private BigDecimal expressPrice;
    private String discount;
    private String imgUrl;
    private Integer state = StateEnum.STATE_YES.getCode();
    private Integer recommend;

}
