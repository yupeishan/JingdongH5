package com.example.jingdong.test;

import com.example.jingdong.pojo.Product;
import com.example.jingdong.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void save() {
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByShopId() {
    }

    @Test
    public void findShopProduct(){
        Integer shopId = 1;
        String type = "";
        String name = "";
        Integer state = -1;
        Pageable pageable = PageRequest.of(0,10, Sort.Direction.DESC,"sales");

        Page<Product> productPage = productService.findShopProduct(shopId, type, name, state, pageable);

        for (Product product : productPage) {
            log.info("product={}",product);
        }
    }





}