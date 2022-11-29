package com.example.jingdong.controller.seller;

import com.example.jingdong.domain.Result;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.enums.SellerAdminRightsEnum;
import com.example.jingdong.enums.StateEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.form.SellerAddForm;
import com.example.jingdong.form.SellerForm;
import com.example.jingdong.pojo.Seller;
import com.example.jingdong.service.SellerService;
import com.example.jingdong.utils.Md5Util;
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
@RequestMapping("/seller/admin")
public class SellerAdminController {

    @Autowired
    private SellerService sellerService;


    @GetMapping("listPage")
    public ModelAndView admin(){
        return new ModelAndView("shop/admin-list");
    }

    //返回停用管理员列表视图页面
    @GetMapping("offShelve")
    public ModelAndView offShelveList(){
        return new ModelAndView("shop/admin-offShelve");
    }


    @GetMapping("list")
    @ResponseBody
    public Result<Object> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                               @RequestParam(value = "state", defaultValue = "1") Integer state,
                               @RequestParam(value = "realName", defaultValue = "") String realName){

        //构建分页对象
        Pageable pageable = PageRequest.of(page-1,limit);
        //查找数据
        Page<SellerVO> sellerVOPage = sellerService.findShopAdmin(realName, state, pageable);


        return ResultUtil.success(sellerVOPage);
    }



    //返回管理员编辑视图页面
    @GetMapping("editPage")
    public ModelAndView editPage(@RequestParam(value = "sellerId",required = false) Integer id){
        SellerForm sellerForm = new SellerForm();
        if (id != null){
            //根据id查询到要更改的用户的信息
            Seller seller = sellerService.findBySellerId(id);
            BeanUtils.copyProperties(seller,sellerForm);
        }

        //卖家管理员权限枚举类信息
        SellerAdminRightsEnum[] adminRightsEnums = SellerAdminRightsEnum.values();

        //返回更改页面以及用户数据
        ModelAndView mv = new ModelAndView();
        mv.addObject("sellerForm",sellerForm);
        mv.addObject("adminRightsEnums",adminRightsEnums);
        mv.setViewName("shop/admin-edit");

        return mv;
    }


    @PostMapping("edit")
    @ResponseBody
    public Result<Seller> list(HttpServletRequest request,
                               @Valid SellerForm sellerForm,
                               BindingResult bindingResult){
        //校验数据是否有错误
        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        //校验用户是否存在
        Seller seller = sellerService.findBySellerId(sellerForm.getSellerId());
        if (seller == null){
            throw new SellException(ResultEnum.USER_NOT_EXIST_OR_AUTH);
        }

        //获取请求中当前操作用户的信息
        //判断state是否为0 以及提交的信息是否和用户的信息一致
        //防止禁用自己的账户
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");
        if (sellerVO.getSellerId().equals(sellerForm.getSellerId())){
            if (StateEnum.STATE_NO.getCode().equals(sellerForm.getState())){
                throw new SellException(ResultEnum.UNABLE_DISABLE_YOURSELF);
            }
        }

        //复制更新的信息
        BeanUtils.copyProperties(sellerForm,seller);
        //保存
        Seller save = sellerService.save(seller);

        if (save == null){
            throw new SellException(ResultEnum.ADMIN_UPDATE_FAIL);
        }

        return ResultUtil.success(sellerForm);
    }

    @PostMapping("delete")
    @ResponseBody
    public Result<String> delete(HttpServletRequest request,
                                 @RequestParam("sellerId") Integer sellerId){
        //获取登录的用户的信息
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");

        //判断要删除的账户是否是当前登录的用户，防止删除自己
        if (sellerVO.getSellerId().equals(sellerId)){
            throw new SellException(ResultEnum.UNABLE_DELETE_YOURSELF);
        }


        sellerService.delete(sellerId);

        return ResultUtil.success();
    }

    @GetMapping("addPage")
    public ModelAndView addPage(){
        //卖家管理员枚举类信息
        SellerAdminRightsEnum[] adminRightsEnums = SellerAdminRightsEnum.values();

        //返回更改页面以及用户数据
        ModelAndView mv = new ModelAndView();
        mv.addObject("adminRightsEnums",adminRightsEnums);
        mv.setViewName("shop/admin-add");

        return mv;
    }

    @PostMapping("add")
    @ResponseBody
    public Result<Seller> add(HttpServletRequest request,
                              @Valid SellerAddForm sellerAddForm,
                              BindingResult bindingResult){

        //校验数据是否有错误
        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        //验证重复密码
        if (!sellerAddForm.getPassword().equals(sellerAddForm.getRePassword())){
            throw new SellException(ResultEnum.REPEAT_PASSWORD_ERROR);
        }

        //获取登录的用户的信息
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");

        Seller seller = new Seller();
        BeanUtils.copyProperties(sellerAddForm,seller);

        //添加用户的店铺id即为当前操作用户的店铺id
        seller.setShopId(sellerVO.getShopId());
        //md5加密密码
        seller.setPassword(Md5Util.md5(seller.getPassword()));

        //保存至数据库
        Seller save = sellerService.save(seller);

        if (save == null){
            throw new SellException(ResultEnum.UNKNOWN_ERROR);
        }

        return ResultUtil.success(save);
    }





}
