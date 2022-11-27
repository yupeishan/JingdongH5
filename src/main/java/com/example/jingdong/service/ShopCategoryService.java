package com.example.jingdong.service;

import com.example.jingdong.pojo.ShopCategory;

import java.util.List;

public interface ShopCategoryService {


    ShopCategory save(ShopCategory shopCategory);


    ShopCategory findOne(Integer id);


    List<ShopCategory> findAll();


    List<ShopCategory> findAllByState(Integer state);



}
