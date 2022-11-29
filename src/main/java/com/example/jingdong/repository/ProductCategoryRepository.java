package com.example.jingdong.repository;

import com.example.jingdong.pojo.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {


    List<ProductCategory> findByShopIdAndState(Integer shopId,Integer state);


    /**
     * 商家后台搜索栏 条件查询1
     * @param shopId    店铺id
     * @param name      商品名称模糊查询
     * @param state     商品状态
     * @param pageable  分页对象
     * @return          Page<ProductCategory
     */
    Page<ProductCategory> findAllByShopIdAndNameLikeAndState(Integer shopId, String name, Integer state, Pageable pageable);

    /**
     * 商家后台搜索栏 条件查询2
     * state为Integer类型 无法拼接 % 进行模糊查询
     * @param shopId    店铺id
     * @param name      商品名称模糊查询
     * @param pageable  分页对象
     * @return          Page<ProductCategory
     */
    Page<ProductCategory> findAllByShopIdAndNameLike(Integer shopId, String name, Pageable pageable);

}
