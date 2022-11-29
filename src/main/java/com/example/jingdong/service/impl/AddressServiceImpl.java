package com.example.jingdong.service.impl;

import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.enums.StateEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.pojo.Address;
import com.example.jingdong.pojo.User;
import com.example.jingdong.repository.AddressRepository;
import com.example.jingdong.repository.UserRepository;
import com.example.jingdong.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Address addAddress(Address address) {
        //验证用户是否存在
        Optional<User> optionalUser = userRepository.findById(address.getUserId());
        if ( !optionalUser.isPresent() ){
            throw new SellException(ResultEnum.USER_NOT_EXIST);
        }

        Address defaultAddress = null;

        //当用户添加的地址为默认地址时
        if (StateEnum.DEFAULT_ADDR_YES.getCode().equals(address.getIsDefault())){
            //查询该用户是否已有默认地址
            defaultAddress = repository.findAddressByUserIdAndIsDefault(address.getUserId(),
                                        StateEnum.DEFAULT_ADDR_YES.getCode());
        }
        //已有默认地址
        if (defaultAddress != null){
            //将默认地址改为普通地址
            defaultAddress.setIsDefault(StateEnum.DEFAULT_ADDR_NO.getCode());
            repository.save(defaultAddress);
        }

        //保存此地址为默认地址
        return repository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        //验证地址是否存在
        Optional<Address> result = repository.findById(address.getId());
        if ( !result.isPresent() ){
            throw new SellException(ResultEnum.ADDRESS_NOT_EXIST);
        }

        //验证用户是否一致
        if (!Objects.equals(result.get().getUserId(), address.getUserId())){
            throw new SellException(ResultEnum.ADDRESS_USER_ERROR);
        }
        Address newAddress = new Address();
        BeanUtils.copyProperties(address,newAddress);

        /*
        如果前端传过来的addressForm.getIsDefault()为1 数据库查询的结果 isDefault为0
        说明要把当前地址设为默认地址 这时需要把数据库中曾经的默认地址设为普通地址
        */
        if ( StateEnum.DEFAULT_ADDR_YES.getCode().equals(address.getIsDefault())
            && StateEnum.DEFAULT_ADDR_NO.getCode().equals( result.get().getIsDefault() ) ){
            //查询到此用户的默认地址
            Address defaultAddress = repository.findAddressByUserIdAndIsDefault(address.getUserId(),StateEnum.DEFAULT_ADDR_YES.getCode());
            if (defaultAddress!=null){
                //如果不为null 将其设为普通地址后保存
                defaultAddress.setIsDefault(StateEnum.DEFAULT_ADDR_NO.getCode());
                repository.save(defaultAddress);
            }
        }

        return repository.save(newAddress);
    }

    @Override
    public Address getDefaultAddress(Integer userId) {
        return repository.findAddressByUserIdAndIsDefault(userId,StateEnum.DEFAULT_ADDR_YES.getCode());
    }

    @Override
    public List<Address> getAllAddress(Integer userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public void deleteAddress(Integer userId, Integer addressId) {
        Optional<Address> optionalAddress = repository.findById(addressId);
        if (!optionalAddress.isPresent()){
            throw new SellException(ResultEnum.ADDRESS_NOT_EXIST);
        }
        if (!optionalAddress.get().getUserId().equals(userId)){
            throw new SellException(ResultEnum.ADDRESS_USER_ERROR);
        }
        //JPA删除无返回值 删除失败报错捕获
        try {
            repository.deleteById(addressId);
        }catch (Exception e){
            throw new SellException(ResultEnum.ADDRESS_UPDATE_ERROR);
        }

    }


}
