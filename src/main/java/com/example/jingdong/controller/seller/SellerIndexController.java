package com.example.jingdong.controller.seller;

import com.example.jingdong.service.OrderService;
import com.example.jingdong.service.ShopService;
import com.example.jingdong.vo.SellerVO;
import com.example.jingdong.vo.ShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/seller")
public class SellerIndexController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private OrderService orderService;

    //后台管理首页
    @GetMapping("index")
    public ModelAndView index(HttpServletRequest request){

        //获取请求中的用户信息
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");

        ModelAndView mv = new ModelAndView();
        mv.addObject("sellerVO",sellerVO);
        mv.setViewName("shop/index");
        return mv;
    }


    //后台管理 欢迎页面
    @GetMapping("welcome/{shopId}")
    @ResponseBody
    public ModelAndView welcome(@PathVariable("shopId") Integer shopId,
                          HttpServletRequest request){
        //获取请求中的用户信息
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");

        //查询店铺信息
        ShopVO shopVO = shopService.findShopVO(sellerVO.getShopId());

        //首页展示本月销售数据
        //本月销量，本月销售额，待发货订单数量，运输中订单数量
        Map<String, Object> shopInfoMap = orderService.findShopInfo(shopId);
        shopInfoMap.put("sellerVO",sellerVO);
        shopInfoMap.put("shopVO",shopVO);

        ModelAndView mv = new ModelAndView();
        mv.addAllObjects(shopInfoMap);
        mv.setViewName("shop/welcome");
        return mv;
    }



}




