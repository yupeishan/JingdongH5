package com.example.jingdong.repository;

import com.example.jingdong.pojo.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Integer> {


    Seller findByOpenId(String openID);

    Seller findByUsername(String username);

    Page<Seller> findAllByRealNameLikeAndState(String realName, Integer state, Pageable pageable);
}
