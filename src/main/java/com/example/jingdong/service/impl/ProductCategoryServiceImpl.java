package com.example.jingdong.service.impl;

import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.pojo.ProductCategory;
import com.example.jingdong.repository.ProductCategoryRepository;
import com.example.jingdong.service.ProductCategoryService;
import com.example.jingdong.vo.SellerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;


    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    public ProductCategory findOne(Integer id) {
        return repository.getReferenceById(id);
    }

    //用户界面查询店铺信息
    @Override
    public List<ProductCategory> findByShopIdAndState(Integer shopId, Integer State) {
        return repository.findByShopIdAndState(shopId,State);
    }

    //商家后台条件查询商品分类
    @Override
    public Page<ProductCategory> findShopCategory(Integer shopId, String name, Integer state, Pageable pageable) {

        return repository.findAllByShopIdAndNameLikeAndState(shopId,"%"+name+"%",state,pageable);
    }

    @Override
    public void delete(Integer cateId, SellerVO sellerVO) {
        Optional<ProductCategory> optional = repository.findById(cateId);
        if (!optional.isPresent()){
            throw new SellException(ResultEnum.PRODUCT_CATEGORY_NOT_EXIST);
        }

        //判断当前操作的用户和当前分类是否属于同一店铺 防止越权
        if (!optional.get().getShopId().equals(sellerVO.getShopId())){
            throw new SellException(ResultEnum.ILLEGAL_OPERATION);
        }

        //删除商品
        try {
            repository.deleteById(cateId);
        }catch (Exception e){
            throw new SellException(ResultEnum.PRODUCT_EDIT_FAIL);
        }
    }

}
