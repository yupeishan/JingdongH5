package com.example.jingdong.service;

import com.example.jingdong.pojo.Shop;
import com.example.jingdong.vo.ShopVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShopService {

    //新增店铺
    Shop save(Shop shop);

    //根据id查找店铺
    Shop findOne(Integer id);

    //根据分类id查询该分类下所有店铺
    List<Shop> findShopsByCategory(Integer cateId);

    //查找所有店铺
    Page<Shop> findAll(Pageable pageable);

    //查找推荐的店铺 并且店铺状态为正常（1）
    Page<Shop> findAllByRecommend(Pageable pageable,Integer recommend);

    //根据店铺id 查询组装shopVO对象
    ShopVO findShopVO(Integer id);




}
