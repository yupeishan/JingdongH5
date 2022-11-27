package com.example.jingdong.service;

import com.example.jingdong.pojo.Address;

import java.util.List;

public interface AddressService {

    //添加地址
    Address addAddress(Address address);

    //更新地址
    Address updateAddress(Address address);

    //获取默认地址
    Address getDefaultAddress(Integer userId);

    //获取用户所有地址
    List<Address> getAllAddress(Integer userId);

    //删除地址
    void deleteAddress(Integer userId,Integer addressId);


}
