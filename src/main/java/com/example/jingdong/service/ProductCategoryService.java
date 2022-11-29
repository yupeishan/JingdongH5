package com.example.jingdong.service;

import com.example.jingdong.pojo.ProductCategory;
import com.example.jingdong.vo.SellerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCategoryService {


    ProductCategory save(ProductCategory productCategory);


    ProductCategory findOne(Integer id);


    /**
     * 用户界面查看店铺所有分类 店铺禁用的分类对用户不展示 故需要state字段
     * @param shopId    要查看的店铺id
     * @param State     分类状态
     * @return          List<ProductCategory>
     */
    List<ProductCategory> findByShopIdAndState(Integer shopId,Integer State);

    /**
     * 商家后台搜索框模糊查询
     * @return  Page<ProductCategory>
     */
    Page<ProductCategory> findShopCategory(Integer shopId, String name, Integer state, Pageable pageable);


    void delete(Integer cateId, SellerVO sellerVO);
}
