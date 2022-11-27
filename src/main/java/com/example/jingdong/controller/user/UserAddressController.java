package com.example.jingdong.controller.user;

import com.example.jingdong.domain.Result;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.form.AddressForm;
import com.example.jingdong.pojo.Address;
import com.example.jingdong.service.AddressService;
import com.example.jingdong.utils.ResultUtil;
import com.example.jingdong.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping( "/user/address" )
@Slf4j
public class UserAddressController {

    @Autowired
    private AddressService addressService;

    //新增收货地址
    @PostMapping("add")
    public Result<Address> addAddress(@Valid AddressForm addressForm,
                                      BindingResult bindingResult,
                                      HttpServletRequest request){
        //验证表单信息
        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.ADDRESS_PARAM_ERROR.getCode(),
                                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        //获取前置切面中保存的用户信息
        UserVO userVO = (UserVO) request.getAttribute("userVO");
        //校验当前操作的用户和要更改的目标是否属于同一人 防止越权
        if (!userVO.getId().equals(addressForm.getUserId())){
            throw new SellException(ResultEnum.ILLEGAL_OPERATION);
        }

        Address address = new Address();
        BeanUtils.copyProperties(addressForm,address);

        Address result = addressService.addAddress(address);
        if (result == null){
            throw new SellException(ResultEnum.ADDRESS_ADD_ERROR);
        }

        return ResultUtil.success(result);
    }


    //更新地址
    @PostMapping("update")
    public Result<Address> updateAddress(@Valid AddressForm addressForm,
                                         BindingResult bindingResult,
                                         HttpServletRequest request){
        //校验表单信息
        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.ADDRESS_PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        //获取前置切面中保存的用户信息
        UserVO userVO = (UserVO) request.getAttribute("userVO");
        //校验当前操作的用户和要更改的目标是否属于同一人 防止越权
        if (!userVO.getId().equals(addressForm.getUserId())){
            throw new SellException(ResultEnum.ILLEGAL_OPERATION);
        }

        Address address = new Address();
        BeanUtils.copyProperties(addressForm,address);

        Address result = addressService.updateAddress(address);
        if (result == null){
            throw new SellException(ResultEnum.ADDRESS_ADD_ERROR);
        }
        return ResultUtil.success(result);
    }


    //查找获取用户默认地址
    @GetMapping("default/{userId}")
    public Result<Address> getDefaultAddress(@PathVariable("userId") Integer userId){
        if (userId == null || userId <= 0){
            throw new SellException(ResultEnum.ADDRESS_PARAM_ERROR);
        }

        Address result = addressService.getDefaultAddress(userId);
        if (result == null){
            throw new SellException(ResultEnum.ADDRESS_DEFAULT_NOT_EXIST);
        }
        return ResultUtil.success(result);
    }


    //查找用户所有地址
    @GetMapping("all/{userId}")
    public Result<Address> getAllAddress( @PathVariable("userId") Integer userId ){

        if (userId == null || userId <= 0 ){
            throw new SellException(ResultEnum.ADDRESS_PARAM_ERROR);
        }

        List<Address> result = addressService.getAllAddress(userId);
        if (result == null){
            throw new SellException(ResultEnum.ADDRESS_ADD_ERROR);
        }
        return ResultUtil.success(result);
    }


    @PostMapping("delete/{userId}")
    public Result<Object> delete( @PathVariable("userId") Integer userId ,
                                  @RequestParam("id") Integer id){
        addressService.deleteAddress(userId,id);
        return ResultUtil.success();
    }






}
