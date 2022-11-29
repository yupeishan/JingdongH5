package com.example.jingdong.repository;

import com.example.jingdong.pojo.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {


    //根据订单号查询
    Order findByOrderNumber(String orderNo);


    //根据openId查询订单分页
    Page<Order> findByUserId(Integer userId , Pageable pageable);


    /**
     * 商家后台条件查询订单方法1
     * @param shopId        店铺Id
     * @param OrderState    订单状态
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param orderNumber   订单号模糊查询
     * @param pageable      分页对象
     * @return              Page<Order>
     */
    Page<Order> findAllByShopIdAndOrderStateIsAndCreateTimeBetweenAndOrderNumberLike(Integer shopId ,Integer OrderState ,String startTime ,String endTime ,String orderNumber ,Pageable pageable);


    /**
     * 商家后台条件查询方法2
     * 解决orderState为空时查询不到的问题
     * 条件查询时 前端传值orderState可能为空 这时需要查询所有状态的订单
     * @param shopId        店铺Id
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param orderNumber   订单号模糊查询
     * @param pageable      分页对象
     * @return              Page<Order>
     */
    Page<Order> findAllByShopIdAndCreateTimeBetweenAndOrderNumberLike(Integer shopId ,String startTime ,String endTime ,String orderNumber ,Pageable pageable);


    /**
     * 商家后台首页查询本月1号至今订单数据
     * @param shopId        店铺id
     * @param orderState    订单状态
     * @param createTime    本月开始时间
     * @return              List<OrderVO>
     */
    List<Order> findAllByShopIdAndOrderStateAndCreateTimeAfter(Integer shopId, Integer orderState, String createTime);


}
