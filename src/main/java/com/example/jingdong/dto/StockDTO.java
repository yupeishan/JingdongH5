package com.example.jingdong.dto;

import lombok.Data;

@Data
public class StockDTO {

    //商品id
    private Integer productId;

    //商品名称
    private String productName;

    //商品购买数量
    private Integer productQuantity;

    public StockDTO(Integer productId, String productName, Integer productQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
    }
}
