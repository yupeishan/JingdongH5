package com.example.jingdong.controller.user;

import com.example.jingdong.converter.Converter;
import com.example.jingdong.domain.Result;
import com.example.jingdong.dto.OrderDTO;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.form.OrderForm;
import com.example.jingdong.service.OrderService;
import com.example.jingdong.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/user/order")
public class UserOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单接口
    @PostMapping("/create")
    public Result<OrderDTO> create(@Validated OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.ORDER_PARAM_ERROR.getCode() ,
                                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        //将表单对象转换为DTO对象
        OrderDTO orderDTO = Converter.OrderFormToOrderDTO(orderForm);
        //创建订单
        OrderDTO result = orderService.createOrder(orderDTO);

        return ResultUtil.success(result);
    }


    //查询用户订单接口
    @GetMapping("all")
    public Result<List<OrderDTO>> getOrders(@RequestParam("userId") Integer userId,
                                            @RequestParam(value = "page",defaultValue = "1") Integer page,
                                            @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (userId == null){
            throw new SellException(ResultEnum.USER_PARAMS_ERROR);
        }

        PageRequest pageRequest = PageRequest.of(page-1,size, Sort.Direction.DESC,"createTime");
        Page<OrderDTO> orderDTOPage = orderService.findOrderList(userId, pageRequest);

        return ResultUtil.success(orderDTOPage.getContent());
    }

    //查看订单详情
    @GetMapping("detail")
    public Result<OrderDTO> getOrderDetail(@RequestParam("orderNumber") String orderNumber){
        OrderDTO orderDTO = orderService.findOne(orderNumber);

        return ResultUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("cancel")
    public Result<OrderDTO> cancelOrder(@RequestParam("orderNumber") String orderNumber){

        if (null == orderNumber || "".equals(orderNumber)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        OrderDTO orderDTO = orderService.cancelOrder(orderNumber);

        return ResultUtil.success(orderDTO);
    }

    //用户确认收货 完成订单
    @PostMapping("finish")
    public Result<OrderDTO> finishOrder(@RequestParam("orderNumber") String orderNumber){

        if (null == orderNumber || "".equals(orderNumber)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        OrderDTO orderDTO = orderService.finishOrder(orderNumber);

        return ResultUtil.success(orderDTO);
    }

    //模拟用户付款 修改订单状态为已支付
    @PostMapping("pay")
    public Result<OrderDTO> payOrder(@RequestParam("orderNumber") String orderNumber,
                                     @RequestParam("openId") String openId){

        if (null == orderNumber || "".equals(orderNumber)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        if (null == openId || "".equals(openId)){
            throw new SellException(ResultEnum.USER_PARAMS_ERROR);
        }

        //模拟支付 微信的交易流水号暂时写死代替
        OrderDTO orderDTO = orderService.payForOrder(orderNumber, openId ,"123465789");

        return ResultUtil.success(orderDTO);
    }

    //模拟用户退款 修改订单状态为已退款
    @PostMapping("refund")
    public Result<OrderDTO> refundOrder(@RequestParam("orderNumber") String orderNumber,
                                        @RequestParam("openId") String openId){

        if (null == orderNumber || "".equals(orderNumber)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        if (null == openId || "".equals(openId)){
            throw new SellException(ResultEnum.USER_PARAMS_ERROR);
        }

        OrderDTO orderDTO = orderService.refundOrder(orderNumber, openId);

        return ResultUtil.success(orderDTO);
    }


}
