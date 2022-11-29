package com.example.jingdong.pojo;

import com.example.jingdong.enums.StateEnum;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table( name = "shop_category")
@Proxy( lazy = false )
@Data
public class ShopCategory {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String imgUrl;
    private Integer state = StateEnum.STATE_YES.getCode();

}
