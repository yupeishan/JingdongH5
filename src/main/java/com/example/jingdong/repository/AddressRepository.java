package com.example.jingdong.repository;

import com.example.jingdong.pojo.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {

    //查询用户默认地址
    Address findAddressByUserIdAndIsDefault( Integer userId,Integer isDefault );


    //查询用户的所有地址
    List<Address> findAllByUserId(Integer userId);


}
