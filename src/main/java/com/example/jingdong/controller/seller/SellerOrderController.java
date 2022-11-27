package com.example.jingdong.controller.seller;

import com.example.jingdong.constant.Const;
import com.example.jingdong.domain.Result;
import com.example.jingdong.dto.OrderDTO;
import com.example.jingdong.enums.OrderStateEnum;
import com.example.jingdong.enums.PayStateEnum;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.pojo.OrderDetail;
import com.example.jingdong.service.OrderService;
import com.example.jingdong.utils.EnumUtil;
import com.example.jingdong.utils.ResultUtil;
import com.example.jingdong.vo.OrderVO;
import com.example.jingdong.vo.SellerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;


    //返回订单页面
    @GetMapping("listPage")
    public ModelAndView listPage(){
        //返回订单页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName("shop/order-list");
        return mv;
    }

    //根据店铺id 及筛选条件 查询店铺所有订单
    @GetMapping("list")
    @ResponseBody
    public Result<HashMap<String,Object>> list(HttpServletRequest request,
                                               @RequestParam(value = "page",defaultValue = "1") Integer page,
                                               @RequestParam(value = "limit",defaultValue = "5") Integer limit,
                                               @RequestParam(value = "startTime",defaultValue = Const.START_TIME) String startTime,
                                               @RequestParam(value = "endTime",defaultValue = Const.END_TIME) String endTime,
                                               @RequestParam(value = "orderState",defaultValue = "-1") Integer orderState,
                                               @RequestParam(value = "orderNumber",defaultValue = "") String orderNumber){
        //获取请求中的用户信息
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");

        //构建分页对象
        Pageable pageable = PageRequest.of(page-1,limit, Sort.Direction.DESC,"createTime");
        //条件查询订单信息
        Page<OrderVO> orderVOPage = orderService.findByShop(sellerVO.getShopId() ,orderState ,startTime ,endTime ,orderNumber ,pageable);

        //获取订单状态的全部枚举信息
        Map<Integer, String> orderStateMap = EnumUtil.enumToMap(OrderStateEnum.class);
        //获取支付状态的全部枚举类信息
        Map<Integer, String> payStateMap = EnumUtil.enumToMap(PayStateEnum.class);

        HashMap<String,Object> map = new HashMap<>();

        map.put("orderStateMap",orderStateMap);
        map.put("payStateMap",payStateMap);
        map.put("orderVOPage",orderVOPage);

        return ResultUtil.success(map);
    }


    //查询返回订单详情
    @GetMapping("detail")
    @ResponseBody
    public Result<OrderDetail> detail(@RequestParam("orderNumber") String orderNumber){
        List<OrderDetail> orderDetailList = orderService.findOrderDetailsByOderNumber(orderNumber);

        return ResultUtil.success(orderDetailList);
    }


    //订单发货
    @PostMapping("shipped")
    @ResponseBody
    public Result<Object> shipped(@RequestParam("orderNumber") String orderNumber,
                                   @RequestParam("courierNumber") String courierNumber){

        OrderDTO orderDTO = orderService.orderShipped(orderNumber, courierNumber);

        if (orderDTO==null){
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }

        return ResultUtil.success();
    }


}
