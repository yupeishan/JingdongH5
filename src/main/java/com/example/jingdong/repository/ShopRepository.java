package com.example.jingdong.repository;

import com.example.jingdong.pojo.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Integer> {


    Page<Shop> findAllByRecommendAndStateOrderByIdAsc(Pageable pageable,Integer recommend,Integer state);


}
