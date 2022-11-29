package com.example.jingdong.pojo;

import com.example.jingdong.enums.StateEnum;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Data
@Table( name = "seller" )
@Proxy( lazy = false )
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellerId;
    private Integer shopId;
    private String username;
    private String password;
    private String realName;
    private String openId;
    private Integer privilege;
    private String email;
    private Integer state = StateEnum.STATE_YES.getCode();
    private String lastLogin;

















}
