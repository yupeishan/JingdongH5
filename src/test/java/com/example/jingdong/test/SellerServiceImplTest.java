package com.example.jingdong.test;

import com.example.jingdong.pojo.Seller;
import com.example.jingdong.service.impl.SellerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SellerServiceImplTest {

    @Autowired
    private SellerServiceImpl sellerService;


    @Test
    public void findByOpenId(){
        Seller seller = sellerService.findByOpenId("123456789");
        log.info("seller={}",seller);
    }



}