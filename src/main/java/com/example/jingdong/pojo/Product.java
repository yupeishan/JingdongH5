package com.example.jingdong.pojo;

import com.example.jingdong.enums.StateEnum;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table( name = "product" )
@Proxy( lazy = false )
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer sales;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private Integer stock;
    private String imgUrl;
    private String type;
    private Integer shopId;
    private Integer state = StateEnum.STATE_YES.getCode();

}
