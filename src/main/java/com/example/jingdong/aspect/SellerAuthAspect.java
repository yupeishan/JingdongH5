package com.example.jingdong.aspect;

import com.alibaba.fastjson.JSON;
import com.example.jingdong.constant.CookieConst;
import com.example.jingdong.constant.RedisConst;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.enums.SellerAdminRightsEnum;
import com.example.jingdong.enums.StateEnum;
import com.example.jingdong.exception.RightsException;
import com.example.jingdong.exception.SellerAuthException;
import com.example.jingdong.pojo.Seller;
import com.example.jingdong.service.SellerService;
import com.example.jingdong.utils.CookieUtil;
import com.example.jingdong.vo.SellerVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class SellerAuthAspect {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SellerService sellerService;


    //定义切面 拦截所有店铺后台接口访问
    //排除密码登录 以及扫码登录两个接口 防止登录失败
    @Pointcut("execution(public * com.example.jingdong.controller.seller.Sell*.*(..))" +
            "&& !execution(public * com.example.jingdong.controller.seller.SellerUserController.login(..))"+
            "&& !execution(public * com.example.jingdong.controller.seller.SellerUserController.qrLogin(..))")
    public void checkLogin(){}


    //定义切面 拦截后台管理页面中对管理员数据接口的访问
    @Pointcut("execution(public * com.example.jingdong.controller.seller.SellerAdminController*.*(..))")
    public void checkSellerAdminRights(){}

    /**
     * 执行切面 当卖家用户未登录时 不允许访问后台管理页面
     * SellerAuthException 自定义异常类 未登录时抛出
     * 在handler中进行异常处理 跳转登录页面
     */
    @Before("checkLogin()")
    public void doCheckLogin(){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        assert attributes != null;
        HttpServletResponse response = attributes.getResponse();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.getCookie(CookieConst.TOKEN,request);
        if (cookie == null){
            log.warn("【登录校验】cookie中查不到token");
            throw new SellerAuthException(ResultEnum.NOT_LOGGED_IN.getMessage());
        }

        //cookie中有token 通过token拼接键去redis中查询值
        String sellerJson = stringRedisTemplate.opsForValue().get
                            ( String.format(RedisConst.TOKEN_PREFIX, cookie.getValue()) );

        if (StringUtils.isEmpty(sellerJson)){
            log.warn("【登录校验】redis中查不到seller信息");
            throw new SellerAuthException(ResultEnum.NOT_LOGGED_IN.getMessage());
        }

        //cookie redis都存在
        Seller seller = JSON.parseObject(sellerJson,Seller.class);
        Seller checkSeller = sellerService.findByUsername(seller.getUsername());
        //校验用户是否存在
        if (checkSeller == null){
            log.warn("【登录校验】用户信息错误或用户不存在 提交信息={}",seller);
            log.warn("【登录校验】用户信息错误或用户不存在 checkSeller={}",checkSeller);
            throw new SellerAuthException(ResultEnum.USER_PARAMS_ERROR.getMessage());
        }
        //校验用户状态是否为正常
        if(StateEnum.STATE_NO.getCode().equals(checkSeller.getState())){
            //用户状态为已禁用 清除redis中的健值
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConst.TOKEN_PREFIX,cookie.getValue()) );
            //清除cookie中的token
            assert response != null;
            CookieUtil.setCookie(CookieConst.TOKEN,null,0, response);

            throw new SellerAuthException(ResultEnum.ADMIN_STATE_ERROR.getMessage());
        }

        //赋值用户信息 这里使用sellerVO对象 去掉不宜展示的用户敏感信息
        SellerVO sellerVO = new SellerVO();
        BeanUtils.copyProperties(checkSeller,sellerVO);

        request.setAttribute("sellerVO",sellerVO);
    }


    //执行切面 卖家后台增删改查管路员信息时检查权限
    //只有店铺超级管理员（privilege为0）可以操作
    @Before("checkSellerAdminRights()")
    public void docheckSellerAdminRights(){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        //获取当前登录的用户信息
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");

        //判断当前用户权限是否为超级管理员
        //如果不是 则抛出异常拒绝访问
        if (!Objects.equals(SellerAdminRightsEnum.SUPER_ADMIN.getCode(), sellerVO.getPrivilege())){
            throw new RightsException(ResultEnum.ADMIN_RIGHTS_NOT_ENOUGH.getMessage());
        }

    }







}
