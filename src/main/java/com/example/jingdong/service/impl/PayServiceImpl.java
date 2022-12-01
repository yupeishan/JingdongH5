package com.example.jingdong.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.jingdong.dto.OrderDTO;
import com.example.jingdong.enums.PayStateEnum;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.service.OrderService;
import com.example.jingdong.service.PayService;
import com.example.jingdong.utils.IPUtils;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private OrderService orderService;

    //微信统一下单
    @Override
    public WxPayMpOrderResult create(String orderNumber, HttpServletRequest request) {

        //查询订单
        OrderDTO orderDTO = orderService.findOne(orderNumber);
        if (orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断支付状态是否为未支付
        if (!PayStateEnum.PAY_NOT.getCode().equals(orderDTO.getPayState())){
            throw new SellException(ResultEnum.ORDER_HAS_BEEN_PAID);
        }

        //统一下单
        try {
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            orderRequest.setBody(orderDTO.getShopName());
            orderRequest.setOutTradeNo(orderDTO.getOrderNumber());
            orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(orderDTO.getOrderAmount().toString()));
            orderRequest.setOpenid(orderDTO.getUserOpenId());
            orderRequest.setSpbillCreateIp(IPUtils.getIpAddr(request));
            orderRequest.setTradeType(WxPayConstants.TradeType.JSAPI);

            return wxPayService.createOrder(orderRequest);
        } catch (Exception e) {
            log.error("微信支付失败！ 订单号:{}，原因:{}",orderDTO.getOrderNumber(),e.getMessage());
        }

        return null;
    }


    @Override
    public String notify(HttpServletRequest request) {
        WxPayOrderNotifyResult payResponse;

        //解析xml文件
        try {
            String xmlResult = IOUtils.toString(request.getInputStream() ,
                                                request.getCharacterEncoding());
            payResponse = wxPayService.parseOrderNotifyResult(xmlResult);
            log.info("【微信支付】异步通知 payResponse={}", JSON.toJSON(payResponse));
        } catch (Exception e) {
            log.error("【微信支付】微信回调结果异常，原因={}",e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }

        //判断订单是否存在
        String orderNo = payResponse.getOutTradeNo();
        OrderDTO orderDTO = orderService.findOne(orderNo);
        if (orderDTO == null){
            log.error("【微信支付】异步通知，订单不存在，orderNo={}",orderNo);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //支付状态
        if (!PayStateEnum.PAY_NOT.getCode().equals(orderDTO.getPayState())){
            log.error("【微信支付】异步通知，支付状态不正确，payState={}",orderDTO.getPayState());
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }

        //支付人
        if (!orderDTO.getUserOpenId().equals(payResponse.getOpenid())){
            log.error("【微信支付】异步通知，openId不一致，openId={}",payResponse.getOpenid());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_OPENID_VARIFY_ERROR);
        }

        //判断金额是否一致
        //单位分转为元
        BigDecimal totalAmount = new BigDecimal(BaseWxPayResult.fenToYuan(payResponse.getTotalFee()));
        if (orderDTO.getOrderAmount().compareTo(totalAmount) != 0){
            log.error("【微信支付】异步通知,订单金额不一致，orderId={}，微信通知金额={}，系统金额={}" ,
                    payResponse.getOpenid(),totalAmount,orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VARIFY_ERROR);
        }
        String returnMsg = payResponse.getReturnMsg();
        log.info("支付结果={}",returnMsg);

        //流水交易号 需要保存至数据库中
        String transactionNo = payResponse.getTransactionId();
        //修改支付状态，支付时间，支付流水号
        orderService.payForOrder(orderNo,orderDTO.getUserOpenId(),transactionNo);
        log.info("【微信支付】异步通知，支付成功，payResponse={}",JSON.toJSON(WxPayNotifyResponse.success("支付成功")));

        return WxPayNotifyResponse.success("支付成功");
    }


    //微信退款
    @Override
    public WxPayRefundResult refund(OrderDTO orderDTO) {

        WxPayRefundResult refundResult;

        WxPayRefundRequest refundRequest = new WxPayRefundRequest();
        //订单号
        refundRequest.setOutTradeNo(orderDTO.getOrderNumber());
        //退款单号 以订单号代替
        refundRequest.setOutRefundNo(orderDTO.getOrderNumber());
        //订单总金额
        refundRequest.setTotalFee(BaseWxPayRequest.yuanToFen(orderDTO.getOrderAmount().toString()));
        //退款总金额
        refundRequest.setRefundFee(BaseWxPayRequest.yuanToFen(orderDTO.getOrderAmount().toString()));
        //退款用户openId
        refundRequest.setOpUserId(orderDTO.getUserOpenId());

        try {
            refundResult = wxPayService.refund(refundRequest);
            log.info("message={}",refundResult.getReturnMsg());
        } catch (Exception e) {
            log.error("微信退款失败！订单号={},原因={}",orderDTO.getOrderNumber(),e.getMessage());
            throw new SellException(ResultEnum.WXPAY_REFUND_ERROR.getCode(),e.getMessage());
        }

        return refundResult;
    }




}
