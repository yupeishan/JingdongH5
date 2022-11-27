package com.example.jingdong.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductBo {

    private Integer id;
    private String name;
    private Integer sales;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private String imgUrl;






}
