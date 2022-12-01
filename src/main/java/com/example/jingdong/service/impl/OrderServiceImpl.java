package com.example.jingdong.service.impl;

import com.example.jingdong.dto.OrderDTO;
import com.example.jingdong.dto.StockDTO;
import com.example.jingdong.enums.OrderStateEnum;
import com.example.jingdong.enums.PayStateEnum;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.pojo.*;
import com.example.jingdong.repository.OrderDetailRepository;
import com.example.jingdong.repository.OrderRepository;
import com.example.jingdong.service.OrderService;
import com.example.jingdong.service.ProductService;
import com.example.jingdong.service.UserService;
import com.example.jingdong.utils.CalendarUtil;
import com.example.jingdong.utils.KeyUtil;
import com.example.jingdong.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopServiceImpl shopService;


    //创建订单
    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        //校验下单用户是否存在
        User user = userService.findOne(orderDTO.getUserId());
        if (user == null){
            throw new SellException(ResultEnum.USER_NOT_EXIST);
        }

        //获取随机唯一订单号
        String orderNumber= KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            Product product = productService.findOne(orderDetail.getProductId());
            //判断商品是否存在
            if (product == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //计算订单总价  商品单价*数量+amount
            orderAmount = product.getPrice().multiply(new BigDecimal(orderDetail.getQuantity())).add(orderAmount);

            //订单详情入库
            //订单详情设置订单号
            orderDetail.setOrderNumber(orderNumber);
            //订单项随机获取唯一编号
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            //将数据库查出的商品信息赋值给每个订单项
            BeanUtils.copyProperties(product,orderDetail);
            //订单详情入库
            orderDetailRepository.save(orderDetail);
        }
        //订单入库
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        order.setOrderNumber(orderNumber);
        order.setOrderAmount(orderAmount);
        order.setOrderState(OrderStateEnum.ORDER_WAIT_CONFIRM.getCode());
        order.setPayState(PayStateEnum.PAY_NOT.getCode());
        orderRepository.save(order);

        //返回值属性补全
        BeanUtils.copyProperties(order,orderDTO);

        return orderDTO;
    }

    //查找一个订单
    @Override
    public OrderDTO findOne(String orderNumber) {
        OrderDTO orderDTO = new OrderDTO();
        //根据订单号查找订单
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //根据订单号查找订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderNumber(orderNumber);
        if (orderDetailList == null){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        //组装数据至orderDTO
        BeanUtils.copyProperties(order,orderDTO);
        //设置订单详情
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    //根据用户userId查找该用户所有订单
    @Override
    public Page<OrderDTO> findOrderList(Integer userId , Pageable pageable) {
        //查询出该用户的所有订单
        Page<Order> orderPage = orderRepository.findByUserId(userId, pageable);

        /*
         遍历orderPage 并调用自定义方法converter()
         查询出每一个order对应的orderDetail 转换为orderDTO对象返回
         最终转换为List<OrderDTO>
         */
        List<OrderDTO> orderDTOS = orderPage.stream().map(this::orderToOrderDTO).collect(Collectors.toList());
        return new PageImpl<>(orderDTOS, pageable, orderPage.getTotalElements());
    }


    //取消订单
    @Override
    @Transactional
    public OrderDTO cancelOrder(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //支付状态需为未支付
        if (!PayStateEnum.PAY_NOT.getCode().equals(order.getPayState())){
            throw new SellException(ResultEnum.ORDER_PAY_STATE_ERROR);
        }

        //订单状态需为待确认
        if (!OrderStateEnum.ORDER_WAIT_CONFIRM.getCode().equals(order.getOrderState())){
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }

        //更新状态
        order.setOrderState(OrderStateEnum.ORDER_CANCEL.getCode());
        orderRepository.save(order);

        //返回库存
        //将order转换为orderDtO对象
        OrderDTO orderDTO = orderToOrderDTO(order);
        //lambda表达式将orderDTO对象 转换为stockDTO对象（库存对象）
        List<StockDTO> stockDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                        new StockDTO(e.getProductId(),e.getName(),e.getQuantity())).collect(Collectors.toList());

        //返回库存
        productService.increaseStock(stockDTOList);

        return orderDTO;
    }

    //订单发货
    @Override
    public OrderDTO orderShipped(String orderNumber ,String courierNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断支付状态 已支付的订单才可以发货
        if ( !PayStateEnum.PAY_YES.getCode().equals(order.getPayState()) ){
            throw new SellException(ResultEnum.ORDER_PAY_STATE_ERROR);
        }

        //判断订单状态 未发货的订单才可发货
        if ( !OrderStateEnum.ORDER_SHIPPED_NO.getCode().equals(order.getOrderState()) ){
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }

        //修改订单状态为已发货
        order.setOrderState(OrderStateEnum.ORDER_SHIPPED_YES.getCode());
        //更新运单号
        order.setCourierNumber(courierNumber);
        Order save = orderRepository.save(order);

        return orderToOrderDTO(save);
    }

    //完成订单
    @Override
    @Transactional
    public OrderDTO finishOrder(String orderNumber){
        //判断订单是否存在
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断订单状态 已送达 待确认的订单才可以完成
        if ( !OrderStateEnum.ORDER_HAS_BEEN_DELIVERED.getCode().equals(order.getOrderState()) ){
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }

        //修改订单状态为完成
        order.setOrderState(OrderStateEnum.ORDER_FINISHED.getCode());
        //保存
        orderRepository.save(order);

        //更新商品销量
        OrderDTO orderDTO = orderToOrderDTO(order);
        productService.increaseSales(orderDTO.getOrderDetailList());

        //增加店铺销量
        Shop shop = shopService.findOne(orderDTO.getShopId());
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            shop.setSales(shop.getSales() + orderDetail.getQuantity());
        }
        shopService.save(shop);

        return orderDTO;
    }

    //支付订单
    @Override
    @Transactional
    public OrderDTO payForOrder(String orderNumber ,String openId ,String transactionNumber) {
        //检查订单是否存在
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //检查订单的openId与用户是否一致
        if (!order.getUserOpenId().equals(openId)){
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }

        //判断订单状态 订单状态必须为待确认才可支付
        if (!OrderStateEnum.ORDER_WAIT_CONFIRM.getCode().equals(order.getOrderState())){
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }

        //判断支付状态 支付状态需为未支付
        if (!PayStateEnum.PAY_NOT.getCode().equals(order.getPayState())){
            throw new SellException(ResultEnum.ORDER_HAS_BEEN_PAID);
        }

        //微信生成的交易流水号不可为空
        if (null == transactionNumber || "".equals(transactionNumber)){
            throw new SellException(ResultEnum.ORDER_TRANSACTION_NUMBER_ERROR);
        }

        //修改支付状态为已支付
        order.setPayState(PayStateEnum.PAY_YES.getCode());
        //支付完成 修改订单状态为未发货
        order.setOrderState(OrderStateEnum.ORDER_SHIPPED_NO.getCode());
        //设置支付时间
        String date = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date());
        order.setPayTime(date);
        //保存微信支付交易流水号transactionNumber
        order.setTransactionNumber(transactionNumber);

        order = orderRepository.save(order);
        OrderDTO orderDTO = orderToOrderDTO(order);

        //减少库存  lambda表达式遍历 得到stockDTOList
        List<StockDTO> stockDTOList = orderDTO.getOrderDetailList().stream().map
                (e -> new StockDTO(e.getProductId(),
                        e.getName(),
                        e.getQuantity()) ).collect(Collectors.toList());
        //减少库存
        productService.decreaseStock(stockDTOList);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO refundOrder(String orderNumber, String openId) {
        //检查订单是否存在
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //检查订单的openId与用户是否一致
        if (!order.getUserOpenId().equals(openId)){
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }

        //判断订单状态 订单状态必须为未发货才可退款
        if (!OrderStateEnum.ORDER_SHIPPED_NO.getCode().equals(order.getOrderState())){
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }

        //判断支付状态 支付状态需为已支付
        if (!PayStateEnum.PAY_YES.getCode().equals(order.getPayState())){
            throw new SellException(ResultEnum.ORDER_PAY_NOT);
        }

        //修改订单状态为已退款
        order.setOrderState(OrderStateEnum.ORDER_REFUNDED.getCode());
        //保存
        Order save = orderRepository.save(order);

        //返回库存
        //将order转换为orderDtO对象
        OrderDTO orderDTO = orderToOrderDTO(save);
        //lambda表达式将orderDTO对象 转换为stockDTO对象（库存对象）
        List<StockDTO> stockDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new StockDTO(e.getProductId(),e.getName(),e.getQuantity())).collect(Collectors.toList());

        //返回库存
        productService.increaseStock(stockDTOList);

        //转换为orderDTO对象返回
        return orderToOrderDTO(save);
    }


    //商家管理后台 条件查询
    @Override
    public Page<OrderVO> findByShop(Integer shopId, Integer orderState, String startTime, String endTime, String orderNumber, Pageable pageable) {
        //判断店铺是否存在
        Shop shop = shopService.findOne(shopId);
        if (null == shop){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }

        //查询店铺订单 当orderState为-1时 说明用户未选择订单状态 忽略此条件  -1为订单接口指定的默认值
        Page<Order> orderPage;
        if (orderState == -1){
            //根据shopId，startTime，endTime，orderNumber进行查询
            orderPage = orderRepository.findAllByShopIdAndCreateTimeBetweenAndOrderNumberLike(
                    shopId  ,startTime ,endTime ,"%"+orderNumber+"%" ,pageable);
        }else {
            //orderState有输入值，根据shopId，OrderState，startTime，endTime，orderNumber进行查询
            orderPage = orderRepository.findAllByShopIdAndOrderStateIsAndCreateTimeBetweenAndOrderNumberLike(
                                                                shopId ,orderState ,startTime ,endTime ,"%"+orderNumber+"%" ,pageable);
        }

        //将order转换成orderVO对象 去掉用户id openId等不宜展示的信息
        List<OrderVO> orderVOList = orderPage.stream().map
                (this::orderToOrderVO).collect(Collectors.toList());

        return new PageImpl<>(orderVOList, pageable, orderPage.getTotalElements());
    }


    //根据订单号查找订单详情
    @Override
    public List<OrderDetail> findOrderDetailsByOderNumber(String orderNumber) {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderNumber(orderNumber);
        if (orderDetailList == null || orderDetailList.size()<=0){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        return orderDetailList;
    }


    /*商家管理后台 查询本月销售数据统计信息
    本月销量，本月销售额，待发货订单，运输中订单数量*/
    @Override
    public Map<String,Object> findShopInfo(Integer shopId) {
        //判断店铺是否存在
        Shop shop = shopService.findOne(shopId);
        if (null == shop){
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }

        int monthSales = 0;
        BigDecimal monthSaleAmount = BigDecimal.valueOf(0);
        long shippedOrderNum;
        long unshippedOrderNum;


        //获取本月1号的开始时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String monthBegin = formatter.format(CalendarUtil.getThisMonthStart());

        //根据店铺id 查询本月至今全部已支付订单数据
        List<Order> orderList = orderRepository.findAllByShopIdAndOrderStateAndCreateTimeAfter(shopId,
                OrderStateEnum.ORDER_SHIPPED_NO.getCode(),monthBegin);
        //流转换为DTO对象；
        List<OrderDTO> orderDTOS = orderList.stream().map(this::orderToOrderDTO).collect(Collectors.toList());

        for (OrderDTO orderDTO : orderDTOS) {
            //计算总销售额
            monthSaleAmount = monthSaleAmount.add(orderDTO.getOrderAmount());
            //计算总销量
            for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
                monthSales += orderDetail.getQuantity();
            }
        }

        Order order = new Order();
        order.setShopId(shopId);
        order.setPayState(PayStateEnum.PAY_YES.getCode());
        order.setOrderState(OrderStateEnum.ORDER_SHIPPED_NO.getCode());
        //查询待发货订单数量
        unshippedOrderNum = orderRepository.count(Example.of(order));

        //查询已发货订单数量
        order.setOrderState(OrderStateEnum.ORDER_SHIPPED_YES.getCode());
        shippedOrderNum = orderRepository.count(Example.of(order));

        Map<String,Object> map = new HashMap<>();
        map.put("monthSales",monthSales);
        map.put("monthSaleAmount",monthSaleAmount);
        map.put("unshippedOrderNum",unshippedOrderNum);
        map.put("shippedOrderNum",shippedOrderNum);
        return map;
    }



    //根据订单查找订单详情 并赋值转换为orderDTO
    public OrderDTO orderToOrderDTO(Order order){
        //根据订单号查询对应的订单详情
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderNumber(order.getOrderNumber());
        if (orderDetails == null){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        //将订单 订单详情 组装成orderDTO对象返回
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }


    //将order对象转换为orderVO对象  去掉不展示的敏感信息 如用户openid
    public OrderVO orderToOrderVO(Order order){

        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order,orderVO);
        return orderVO;
    }





}
