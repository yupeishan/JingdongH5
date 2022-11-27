package com.example.jingdong.service;

import com.example.jingdong.dto.OrderDTO;
import com.example.jingdong.pojo.OrderDetail;
import com.example.jingdong.vo.OrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface OrderService {


    /**
     * 用户下单
     * @param orderDTO  订单数据传输对象
     * @return          OrderDTO
     */
    OrderDTO createOrder(OrderDTO orderDTO);


    /**
     * 查询单个订单
     * @param orderNumber  订单号
     */
    OrderDTO findOne(String orderNumber);


    /**
     * 查询用户所有订单
     * @param userId    用户Id
     * @param pageable  分页逻辑
     * @return          Page<OrderDTO>
     */
    Page<OrderDTO> findOrderList(Integer userId , Pageable pageable);


    /**
     * 取消订单
     * @param orderNumber  要取消的订单号
     * @return             OrderDTO
     */
    OrderDTO cancelOrder(String orderNumber);

    /**
     * 订单发货
     * @param orderNumber     订单号
     * @param courierNumber   快递单号
     * @return                OrderDTO
     */
    OrderDTO orderShipped(String orderNumber ,String courierNumber);


    /**
     * 完成订单
     * @param orderNumber  订单号
     * @return             OrderDTO
     */
    OrderDTO finishOrder(String orderNumber);


    /**
     * 支付订单
     * @param orderNumber   订单号
     * @param openId        用户openId
     * @param transactionNo 微信支付生成的交易流水号
     * @return              OrderDTO
     */
    OrderDTO payForOrder(String orderNumber ,String openId ,String transactionNo);

    /**
     * 订单退款
     * @param orderNumber   订单号
     * @param openId        用户openId
     * @return              OrderDTO
     */
    OrderDTO refundOrder(String orderNumber ,String openId);


    /**
     * 商家后台条件查询分页订单
     * @param shopId      店铺id
     * @param OrderState  订单状态
     * @param startTime   下单时间
     * @param endTime     下单时间
     * @param orderNumber 订单号模糊查询
     * @param pageable    分页对象
     * @return            Page<OrderVO>
     */
    Page<OrderVO> findByShop(Integer shopId ,Integer OrderState ,String startTime ,String endTime ,String orderNumber ,Pageable pageable);


    /**
     * 查找订单详情
     * @param orderNumber   要查找的订单号
     * @return              OrderDetail集合
     */
    List<OrderDetail> findOrderDetailsByOderNumber(String orderNumber);


    /**
     * 商家管理后台 查询本月销售数据统计信息
     * 本月销量，本月销售额，待发货订单，运输中订单数量
     * @param shopId    店铺id
     * @return          List<OrderVO>
     */
    Map<String,Object> findShopInfo(Integer shopId);



}
