package com.example.jingdong.controller.seller;

import com.example.jingdong.domain.Result;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.form.ProductCategoryForm;
import com.example.jingdong.pojo.ProductCategory;
import com.example.jingdong.service.ProductCategoryService;
import com.example.jingdong.utils.ResultUtil;
import com.example.jingdong.vo.SellerVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/seller/category")
public class SellerProductCateController {

    @Autowired
    private ProductCategoryService cateService;

    //返回店铺商品分类视图页面
    @GetMapping("listPage")
    public ModelAndView listPage(){
        return new ModelAndView("shop/cate-list");
    }

    //返回店铺商品分类视图页面
    @GetMapping("offShelve")
    public ModelAndView offShelve(){
        return new ModelAndView("shop/cate-offShelve");
    }

    //查询商品分类信息
    @GetMapping("list")
    @ResponseBody
    public Result<Object> list(HttpServletRequest request,
                               @RequestParam(value = "page",defaultValue = "1") Integer page,
                               @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                               @RequestParam(value = "state",defaultValue = "1") Integer state,
                               @RequestParam(value = "name",defaultValue = "") String name){

        //获取请求中的用户信息
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");
        //构建分页对象
        Pageable pageable = PageRequest.of(page-1,limit);

        //条件查询店铺分类
        Page<ProductCategory> shopCategories = cateService.findShopCategory(sellerVO.getShopId(), name, state, pageable);

        return ResultUtil.success(shopCategories);
    }


    //返回分类编辑视图页面
    @GetMapping("editPage")
    public ModelAndView editPage(@RequestParam(value = "id",required = false) Integer id){
        ProductCategory category = new ProductCategory();
        if (id != null){
            category = cateService.findOne(id);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("shop/cate-edit");
        mv.addObject("category",category);
        return mv;
    }

    //编辑分类信息
    @PostMapping("edit")
    @ResponseBody
    public Result<Object> edit(HttpServletRequest request,
                               @Valid ProductCategoryForm productCategoryForm,
                               BindingResult bindingResult){
        //参数校验
        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        //获取请求中的用户
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");

        ProductCategory result;

        //id不为空 执行更新操作
        if (productCategoryForm.getId() != null){
            //获取分类信息
            ProductCategory category = cateService.findOne(productCategoryForm.getId());

            //判断当前用户和要操作的数据是否是同一店铺 防止越权
            if (!category.getShopId().equals(sellerVO.getShopId())){
                throw new SellException(ResultEnum.ILLEGAL_OPERATION);
            }
            //执行更新操作
            BeanUtils.copyProperties(productCategoryForm,category);
            result = cateService.save(category);
        }else {
            //id为空 执行新增操作
            ProductCategory category = new ProductCategory();
            BeanUtils.copyProperties(productCategoryForm,category);

            //分类所属店铺id即当前用户店铺id
            category.setShopId(sellerVO.getShopId());
            result = cateService.save(category);
        }

        if (result != null){
            return ResultUtil.success(result);
        }

        return ResultUtil.error(ResultEnum.CATEGORY_UPDATE_FAIL);
    }


    @PostMapping("delete")
    @ResponseBody
    public Result<Object> delete(HttpServletRequest request,
                                 @RequestParam("cateId") Integer cateId ){

        //获取请求中的用户 用于权限判断
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");

        //JPA删除无返回值 在service方法中进行了try catch抛出异常
        cateService.delete(cateId,sellerVO);

        //未抛出异常 说明删除成功 返回信息
        return ResultUtil.success();
    }


}
