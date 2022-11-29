package com.example.jingdong.pojo;

import com.example.jingdong.enums.StateEnum;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table( name = "product_category" )
@Proxy( lazy = false )
public class ProductCategory {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer shopId;
    @NotNull(message = "分类名称不可为空")
    private String name;
    @NotNull(message = "分类类型不可为空")
    private String type;
    private Integer state = StateEnum.STATE_YES.getCode();
}
