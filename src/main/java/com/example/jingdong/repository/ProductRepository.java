package com.example.jingdong.repository;

import com.example.jingdong.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    /**
     * 根据店铺，商品类型，查询所有商品 按照id排序
     * @param shopId    店铺id
     * @param types     商品类型集合
     * @return          List<Product>
     */
    List<Product> findByShopIdAndTypeInOrderByIdDesc(Integer shopId, List<String> types);


    /**
     * 根据店铺id 商品类型 商品名称 商品状态 查询商品
     * @param shopId    店铺id
     * @param type      商品类型
     * @param name      商品名称
     * @param state     商品状态
     * @param pageable  分页对象
     * @return          List<Product>
     */
    Page<Product> findAllByShopIdAndTypeLikeAndNameLikeAndState(Integer shopId, String type, String name, Integer state, Pageable pageable);


    /**
     * 根据店铺id 商品类型 商品名称查询商品
     * @param shopId    店铺id
     * @param type      商品类型
     * @param name      商品名称
     * @param pageable  分页对象
     * @return          List<Product>
     */
    List<Product> findAllByShopIdAndTypeLikeAndNameLike(Integer shopId, String type, String name, Pageable pageable);



}
