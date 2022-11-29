package com.example.jingdong.service.impl;

import com.example.jingdong.bo.ProductBo;
import com.example.jingdong.bo.ProductCategoryBo;
import com.example.jingdong.dto.StockDTO;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.enums.StateEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.form.ProductForm;
import com.example.jingdong.pojo.OrderDetail;
import com.example.jingdong.pojo.Product;
import com.example.jingdong.pojo.ProductCategory;
import com.example.jingdong.repository.ProductRepository;
import com.example.jingdong.service.ProductCategoryService;
import com.example.jingdong.service.ProductService;
import com.example.jingdong.vo.SellerVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductCategoryService productCategoryService;


    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    //更新商品信息
    @Override
    public Product update(ProductForm productForm) {

        Optional<Product> optional = repository.findById(productForm.getId());
        Product product;
        if (!optional.isPresent()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        product = optional.get();
        BeanUtils.copyProperties(productForm,product);

        return save(product);
    }

    //删除商品
    @Override
    public void delete(Integer productId, SellerVO sellerVO) {
        Optional<Product> optional = repository.findById(productId);
        if (!optional.isPresent()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }

        //判断当前操作的用户和当前商品是否属于同一店铺 防止越权
        if (!optional.get().getShopId().equals(sellerVO.getShopId())){
            throw new SellException(ResultEnum.ILLEGAL_OPERATION);
        }

        //删除商品
        try {
            repository.deleteById(productId);
        }catch (Exception e){
            throw new SellException(ResultEnum.PRODUCT_EDIT_FAIL);
        }
    }


    @Override
    public Product findOne(Integer id) {
        return repository.getReferenceById(id);
    }


    @Override
    public List<ProductCategoryBo> findByShopId(Integer shopId) {
        //先根据shopId查询到该店铺下所有的商品分类
        List<ProductCategory> categoryList =
                productCategoryService.findByShopIdAndState(shopId, StateEnum.STATE_YES.getCode());

        if(categoryList.isEmpty()){
            throw new SellException(ResultEnum.PRODUCT_CATEGORY_NOT_EXIST);
        }

        //lambda表达式 提取所有商品分类的types 转换为集合
        List<String> types =
                categoryList.stream().distinct().map(ProductCategory::getType).collect(Collectors.toList());

        //根据types查询出分类对应的所有的商品
        List<Product> productList = repository.findByShopIdAndTypeInOrderByIdDesc(shopId, types);

        //组装数据
        List<ProductCategoryBo> categoryBoList = new ArrayList<>();
        for (ProductCategory category : categoryList) {
            ProductCategoryBo categoryBo = new ProductCategoryBo();
            //将属性值复制给categoryBo
            BeanUtils.copyProperties(category,categoryBo);
            //筛选出分类对应的商品
            List<ProductBo> productBoList = new ArrayList<>();
            for ( Product product : productList ) {
                if (product.getType().equals(category.getType()) && Objects.equals(product.getState(), StateEnum.STATE_YES.getCode())){
                    ProductBo productBo = new ProductBo();
                    BeanUtils.copyProperties(product,productBo);
                    productBoList.add(productBo);
                }
            }
            categoryBo.setProductBoList(productBoList);
            categoryBoList.add(categoryBo);
        }

        return categoryBoList;
    }

    //减少库存
    @Override
    public void decreaseStock(List<StockDTO> stockDTOList) {
        for (StockDTO stockDTO : stockDTOList) {
            Optional<Product> optional = repository.findById(stockDTO.getProductId());
            if ( !optional.isPresent() ){
                //商品不存在 拼接商品名称 报错
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST.getCode(),
                        stockDTO.getProductName()+ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            }

            Product product = optional.get();
            //判断库存是否充足
            int newStock = product.getStock() - stockDTO.getProductQuantity();
            if (newStock < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_NOT_ENOUGH.getCode(),
                        stockDTO.getProductName()+ResultEnum.PRODUCT_STOCK_NOT_ENOUGH.getMessage());
            }
            //更新商品库存
            product.setStock(newStock);

            repository.save(product);
        }

    }

    //增加库存
    @Override
    public void increaseStock(List<StockDTO> stockDTOList) {
        for (StockDTO stockDTO : stockDTOList) {
            Optional<Product> optional = repository.findById(stockDTO.getProductId());
            if ( !optional.isPresent() ){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Product product = optional.get();

            int newStock = product.getStock() + stockDTO.getProductQuantity();
            product.setStock(newStock);

            repository.save(product);
        }
    }

    @Override
    public void increaseSales(List<OrderDetail> orderDetailList) {
        for (OrderDetail orderDetail : orderDetailList) {
            Optional<Product> byId = repository.findById(orderDetail.getProductId());

            //判断商品是否存在
            if (!byId.isPresent()){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Product product = byId.get();
            product.setSales(orderDetail.getQuantity());
            //更新销量
            repository.save(product);
        }
    }


    //商家后台 查询店铺所有商品
    @Override
    public Page<Product> findShopProduct(Integer shopId, String type, String name, Integer state, Pageable pageable) {

        return repository.findAllByShopIdAndTypeLikeAndNameLikeAndState(shopId, "%"+type+"%", "%"+name+"%", state, pageable);
    }


}
