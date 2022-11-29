package com.example.jingdong.service;

import com.example.jingdong.bo.ProductCategoryBo;
import com.example.jingdong.dto.StockDTO;
import com.example.jingdong.form.ProductForm;
import com.example.jingdong.pojo.OrderDetail;
import com.example.jingdong.pojo.Product;
import com.example.jingdong.vo.SellerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    /**
     * 保存商品信息
     * @param product   商品对象
     * @return          成功则返回更新的商品信息
     */
    Product save(Product product);

    /**
     * 更新商品信息
     * @param productForm   商品表单对象
     * @return              成功则返回更新的商品信息
     */
    Product update(ProductForm productForm);

    /**
     * 删除商品 需判断商品和当前操作的用户是否属于同一用户 防止越权
     * @param productId    需要删除的商品id
     * @param sellerVO     当前操作的用户
     */
    void delete(Integer productId, SellerVO sellerVO);

    /**
     * 根据商品id查询一条数据
     * @param id    商品id
     * @return      查询到的映射对象
     */
    Product findOne(Integer id);



    /**
     * 查询一个店铺下的所有分类及所有商品 按照分类对商品数据进行组装
     * @param shopId 查询的店铺id
     * @return 组装好的BO层对象
     */
    List<ProductCategoryBo> findByShopId(Integer shopId);


    /**
     * 订单创建成功 减少库存
     * @param stockDTOList  需要减库存的对象集合
     */
    void decreaseStock(List<StockDTO> stockDTOList);


    /**
     * 商品增加库存
     * @param stockDTOList  需要增加库存的对象集合
     */
    void increaseStock(List<StockDTO> stockDTOList);

    /**
     * 订单完成 增加商品销量
     * @param orderDetailList  订单详情集合 包含商品id 商品数量信息
     */
    void increaseSales(List<OrderDetail> orderDetailList);

    /**
     * 商家管理后台 条件查询商品信息
     * @param shopId    商家店铺id
     * @param type      商品类型
     * @param name      商品名称
     * @param pageable  分页对象
     * @return          List<Product>
     */
    Page<Product> findShopProduct(Integer shopId, String type, String name, Integer state, Pageable pageable);

}
