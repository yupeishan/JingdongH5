package com.example.jingdong.service;

import com.example.jingdong.dto.OrderDTO;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;

import javax.servlet.http.HttpServletRequest;

public interface PayService {

    //微信发起支付
    WxPayMpOrderResult create(String orderNumber, HttpServletRequest request);


    //支付结果异步回调通知
    String notify(HttpServletRequest request);


    //微信支付退款
    WxPayRefundResult refund(OrderDTO orderDTO);


}
