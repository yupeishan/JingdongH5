package com.example.jingdong.bo;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryBo {

    private Integer id;
    private Integer shopId;
    private String name;
    private String type;
    private List<ProductBo> productBoList;



}
