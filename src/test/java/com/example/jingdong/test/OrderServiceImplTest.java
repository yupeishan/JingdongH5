package com.example.jingdong.test;

import com.example.jingdong.dto.OrderDTO;
import com.example.jingdong.pojo.OrderDetail;
import com.example.jingdong.service.OrderService;
import com.example.jingdong.service.PayService;
import com.example.jingdong.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Test
    public void createOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(1);
        orderDTO.setConsigneeName("王五");
        orderDTO.setTel("18212341234");
        orderDTO.setAddress("浙江省杭州市滨江区123号");
        orderDTO.setUserOpenId("123456789");
        orderDTO.setShopId(1);
        orderDTO.setShopName("沃尔玛");

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId(7);
        orderDetail1.setName("猪肉");
        orderDetail1.setQuantity(2);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId(8);
        orderDetail2.setName("羊肉");
        orderDetail2.setQuantity(1);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(orderDetail1);
        orderDetailList.add(orderDetail2);

        orderDTO.setOrderDetailList(orderDetailList);

        orderService.createOrder(orderDTO);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne("1664012652391149584");
        log.info("orderDTO={}",orderDTO);
    }

    @Test
    public void findOrderList() {
        Pageable pageable = PageRequest.of(0,10);
        Page<OrderDTO> result = orderService.findOrderList(1, pageable);
        log.info("result={}",result.getContent());
    }

    @Test
    public void cancelOrder() {
        OrderDTO orderDTO = orderService.cancelOrder("1664009422734950219");
        log.info("result={}",orderDTO);
    }

    @Test
    public void orderShipped(){
        orderService.orderShipped("1664009422734950213","112233445566");
    }

    @Test
    public void finishOrder() {
        orderService.finishOrder("1664012652391149584");
        orderService.finishOrder("1664185240510339965");
        orderService.finishOrder("1664189197455188399");
    }

    @Test
    public void payForOrder() {
        orderService.payForOrder("1664012652391149581","123456789","111222333444");
    }

    @Test
    public void wxPay(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        payService.create("1664012652391149582",request);
    }


    @Test
    public void findByShopSearch(){

        Pageable pageable = PageRequest.of(0,10);

        Integer shopId = 1;
        Integer orderState = null;
        String startTime = "1970-01-01 00:00:01";
        String endTime = "2038-01-19 03:14:07";
        String orderNumber = "";

        Page<OrderVO> orderVOPage = orderService.findByShop(shopId, orderState,startTime,endTime,orderNumber,pageable);



        for (OrderVO orderVO : orderVOPage) {
            log.info("orderVO={}",orderVO);
        }
    }


    @Test
    public void test(){
        Map<String, Object> map = orderService.findShopInfo(1);
        log.info("map={}",map);
    }

}