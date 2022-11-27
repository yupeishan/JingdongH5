package com.example.jingdong.controller.seller;

import com.example.jingdong.domain.Result;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.enums.StateEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.form.ProductForm;
import com.example.jingdong.pojo.Product;
import com.example.jingdong.pojo.ProductCategory;
import com.example.jingdong.service.ProductCategoryService;
import com.example.jingdong.service.ProductService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    //返回商品视图页面
    @GetMapping("listPage")
    public ModelAndView listPage(){

        return new ModelAndView("shop/product-list");
    }


    //返回下架商品视图页面
    @GetMapping("offShelve")
    public ModelAndView offShelveList(){
        return new ModelAndView("shop/product-offShelve");
    }

    //根据店铺id 及筛选条件 查询店铺所有商品 返回商品信息
    //默认查询状态为上架（1）的商品
    @GetMapping("list")
    @ResponseBody
    public Result<HashMap<String,Object>> list(HttpServletRequest request,
                                               @RequestParam(value = "page",defaultValue = "1") Integer page,
                                               @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                               @RequestParam(value = "state",defaultValue = "1") Integer state,
                                               @RequestParam(value = "type",defaultValue = "") String type,
                                               @RequestParam(value = "name",defaultValue = "") String name){
        //获取请求中的用户信息
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");
        //构建分页对象
        Pageable pageable = PageRequest.of(page-1,limit);

        //条件查询商品信息
        Page<Product> productPage = productService.findShopProduct(sellerVO.getShopId(), type, name,state, pageable);
        //查询店铺商品分类信息 用于前端搜索下拉框
        List<ProductCategory> productCateList = productCategoryService.findByShopIdAndState(sellerVO.getShopId(), StateEnum.STATE_YES.getCode());

        HashMap<String,Object> map = new HashMap<>();
        map.put("productPage",productPage);
        map.put("productCateList",productCateList);

        return ResultUtil.success(map);
    }

    /**
     * 返回商品编辑视图页面
     * 当id有值时为编辑已有商品 需获取商品信息 回传前端
     * 当id没有值时 为新增商品
     * @param id    商品id 为可选参数
     * @return      商品编辑视图页面
     */
    @GetMapping("editPage")
    public ModelAndView edit(HttpServletRequest request,
                             @RequestParam(value = "id",required = false) Integer id){

        //获取商品信息
        Product product = new Product();
        if (id != null){
            product = productService.findOne(id);
        }
        //获取请求中的用户信息
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");
        //获取商品分类信息
        List<ProductCategory> productCateList = productCategoryService.findByShopIdAndState(sellerVO.getShopId(), StateEnum.STATE_YES.getCode());

        ModelAndView mv = new ModelAndView();
        mv.addObject("product",product);
        mv.addObject("productCateList",productCateList);
        mv.setViewName("shop/product-edit");

        return mv;
    }

    //编辑商品信息
    @PostMapping("edit")
    @ResponseBody
    public Result<Object> edit(HttpServletRequest request,
                               @Valid ProductForm productForm,
                               BindingResult bindingResult){
        //输入参数判断
        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                                Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        //获取请求中的用户
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");

        Product result;

        //id不为空 执行更新操作
        if (productForm.getId() != null){
            //获取商品信息
            Product product = productService.findOne(productForm.getId());

            //判断当前用户和要操作的数据是否是同一店铺 防止越权
            if (!product.getShopId().equals(sellerVO.getShopId())){
                throw new SellException(ResultEnum.ILLEGAL_OPERATION);
            }
            //执行更新操作
            result = productService.update(productForm);
        }else {
            //id为空 执行新增操作
            Product product = new Product();
            BeanUtils.copyProperties(productForm,product);
            //初始销量为0
            product.setSales(0);
            //商品所属店铺id即当前用户店铺id
            product.setShopId(sellerVO.getShopId());
            result = productService.save(product);
        }

        //判断是否成功
        if (result != null){
            return ResultUtil.success(result);
        }

        return ResultUtil.error(ResultEnum.PRODUCT_EDIT_FAIL);
    }


    @PostMapping("delete")
    @ResponseBody
    public Result<Object> delete(HttpServletRequest request,
                                 @RequestParam("id") Integer id ){

        //获取请求中的用户 用于权限判断
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");

        //JPA删除无返回值 在service方法中进行了try catch抛出异常
        productService.delete(id,sellerVO);

        //未抛出异常 说明删除成功 返回信息
        return ResultUtil.success();
    }



}
