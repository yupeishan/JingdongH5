package com.example.jingdong.controller.user;

import com.example.jingdong.domain.Result;
import com.example.jingdong.dto.OrderDTO;
import com.example.jingdong.enums.OrderStateEnum;
import com.example.jingdong.enums.PayStateEnum;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.service.OrderService;
import com.example.jingdong.service.PayService;
import com.example.jingdong.utils.ResultUtil;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;


    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderNumber") String orderNumber,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map,
                               HttpServletRequest request){

        if (null == orderNumber || "".equals(orderNumber)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        //统一下单 发起支付
        WxPayMpOrderResult payResponse = payService.create(orderNumber, request);

        //模板中赋值
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pay/create");
        mv.addAllObjects(map);
        return mv;
    }

    //支付后异步回调通知 查询支付结果
    @PostMapping("/notify")
    @ResponseBody
    public String notify(HttpServletRequest request){
        return payService.notify(request);
    }


    //退款
    @GetMapping("/refund")
    @ResponseBody
    public Result<WxPayRefundResult> refund(@RequestParam("orderNumber") String orderNumber){
        //查询订单
        OrderDTO orderDTO = orderService.findOne(orderNumber);
        if (orderDTO == null){
            throw new  SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //订单状态需为 ”未发货“
        if (!OrderStateEnum.ORDER_SHIPPED_NO.getCode().equals(orderDTO.getOrderState())){
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }
        //支付状态需为 ”已支付“
        if (!PayStateEnum.PAY_YES.getCode().equals(orderDTO.getPayState())){
            throw new SellException(ResultEnum.ORDER_PAY_STATE_ERROR);
        }

        return ResultUtil.success(payService.refund(orderDTO));
    }



}
