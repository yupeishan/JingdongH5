package com.example.jingdong.controller.home;

import com.example.jingdong.domain.Result;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.enums.StateEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.pojo.Shop;
import com.example.jingdong.pojo.ShopCategory;
import com.example.jingdong.service.ShopCategoryService;
import com.example.jingdong.service.ShopService;
import com.example.jingdong.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/home")
public class ShopController {

    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private ShopService shopService;

    //获取受特所有店铺分类信息
    @GetMapping("categories")
    public Result<ShopCategory> getShopCategory(){
        List<ShopCategory> categoryList = shopCategoryService.findAll();
        if (categoryList.isEmpty()){
            throw new SellException(ResultEnum.SHOP_CATEGORY_NOT_EXIST);
        }
        return ResultUtil.success(categoryList);
    }

    //获取首页热门店铺
    @GetMapping("hotShops")
    public Result<List<Shop>> getHotShops(@RequestParam(name = "page",defaultValue = "1")Integer page,
                                          @RequestParam(name = "size",defaultValue = "10")Integer size ){
        page = page <= 1 ? 0 : page-1;
        Pageable pageable = PageRequest.of(page,size);
        Page<Shop> pageInfo = shopService.findAllByRecommend(pageable,
                StateEnum.RECOMMEND_YES.getCode());
        if (pageInfo.isEmpty()){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }
        return ResultUtil.success(pageInfo.getContent());
    }

    //根据分类id查找店铺
    @GetMapping("category/shop/{cateId}")
    public Result<List<Shop>> getShopsByCategory( @PathVariable("cateId")Integer cateId ){
        List<Shop> shopList = shopService.findShopsByCategory(cateId);

        if ( shopList == null ){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }

        return ResultUtil.success(shopList);
    }


    //根据id查找店铺
    @GetMapping("shop/{id}")
    public Result<Shop> getShop( @PathVariable("id")Integer id ){
        Shop shop = shopService.findOne(id);
        if ( shop == null ){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }
        return ResultUtil.success(shop);
    }



}
