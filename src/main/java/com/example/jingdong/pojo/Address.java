package com.example.jingdong.pojo;

import com.example.jingdong.enums.StateEnum;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Data
@Table( name = "address" )
@Proxy(lazy = false)
public class Address {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String consigneeName;
    private String tel;
    private String area;
    private String detailedAddress;
    private Integer isDefault = StateEnum.DEFAULT_ADDR_NO.getCode();



}
