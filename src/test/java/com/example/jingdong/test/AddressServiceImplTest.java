package com.example.jingdong.test;

import com.example.jingdong.pojo.Address;
import com.example.jingdong.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class AddressServiceImplTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void addAddress() {
    }

    @Test
    public void updateAddress() {
        Address address = new Address();
        address.setId(1);
        address.setUserId(1);
        address.setConsigneeName("李四");
        address.setTel("18212341234");
        address.setArea("山东省济南市历城区");
        address.setDetailedAddress("鲁商凤凰广场3-601");
        address.setIsDefault(0);
        addressService.updateAddress(address);
    }

    @Test
    public void getDefaultAddress() {
    }

    @Test
    public void getAllAddress() {
        List<Address> list = addressService.getAllAddress(1);
        log.info("list={}",list);
    }
}