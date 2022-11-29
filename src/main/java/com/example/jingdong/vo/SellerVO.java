package com.example.jingdong.vo;

import lombok.Data;

@Data
public class SellerVO {

    private Integer sellerId;
    private Integer shopId;
    private String username;
    private String realName;
    private Integer privilege;
    private String email;
    private Integer state;
    private String lastLogin;

}
