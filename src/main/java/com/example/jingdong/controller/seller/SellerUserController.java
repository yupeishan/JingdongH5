package com.example.jingdong.controller.seller;

import com.alibaba.fastjson.JSON;
import com.example.jingdong.constant.CookieConst;
import com.example.jingdong.constant.RedisConst;
import com.example.jingdong.domain.Result;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.enums.SellerAdminRightsEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.exception.SellerAuthException;
import com.example.jingdong.form.PasswordForm;
import com.example.jingdong.form.SellerLoginForm;
import com.example.jingdong.pojo.Seller;
import com.example.jingdong.service.SellerService;
import com.example.jingdong.utils.CookieUtil;
import com.example.jingdong.utils.ResultUtil;
import com.example.jingdong.vo.SellerVO;
import com.google.code.kaptcha.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerService sellerService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 扫码授权登录 登录成功后将用户信息存至redis
     * 健为随机生成并拼接的token token存在cookie中
     * @param openId    登录用户openId
     * @return          成功后跳转店铺管理页面
     */
    @GetMapping("qrLogin")
    public ModelAndView qrLogin(@RequestParam("openId") String openId,
                              HttpServletResponse response){
        ModelAndView mv = new ModelAndView();

        //根据openId查找卖家信息
        Seller seller = sellerService.findByOpenId(openId);

        if (seller == null){
            throw new SellerAuthException(ResultEnum.USER_NOT_EXIST_OR_AUTH.getMessage());
        }

        //设置卖家用户最后一次登录时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        seller.setLastLogin(formatter.format(date));
        Seller saveResult = sellerService.save(seller);

        //获取随机token
        String token = UUID.randomUUID().toString();
        //token生命周期
        int expire = RedisConst.EXPIRE;
        //将对象序列化
        String sellerStr = JSON.toJSONString(saveResult);

        //将token拼接作为键 seller作为值 存储至redis
        stringRedisTemplate.opsForValue().set(String.format(RedisConst.TOKEN_PREFIX,token),
                sellerStr, expire, TimeUnit.SECONDS);

        //将token存储至cookie
        CookieUtil.setCookie(CookieConst.TOKEN,token,expire,response);

        //授权登录成功 跳转店铺管理页面
        mv.setViewName("redirect:/seller/index");
        return mv;
    }


    /**
     * 账号密码登录
     * @return          成功后跳转店铺管理页面
     */
    @PostMapping("login")
    public ModelAndView login(@Valid SellerLoginForm sellerForm,
                              BindingResult bindingResult,
                              HttpServletResponse response,
                              HttpServletRequest request){
        //校验提交信息是否有错误
        if (bindingResult.hasErrors()){
            throw new SellerAuthException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        //获取并检查验证码 忽略大小写
        String sessionCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (sessionCode == null || !sessionCode.equalsIgnoreCase(sellerForm.getCode())){
            throw new SellerAuthException(ResultEnum.KAPTCHA_CODE_ERROR.getMessage());
        }

        //登录
        Seller seller = sellerService.login(sellerForm.getUsername(), sellerForm.getPassword());

        //获取随机token
        String token = UUID.randomUUID().toString();
        //token生命周期
        int expire = RedisConst.EXPIRE;
        //将对象序列化
        String sellerStr = JSON.toJSONString(seller);

        //将token拼接作为键 seller作为值 存储至redis
        stringRedisTemplate.opsForValue().set(String.format(RedisConst.TOKEN_PREFIX,token),
                sellerStr, expire, TimeUnit.SECONDS);

        //将token存储至cookie
        CookieUtil.setCookie(CookieConst.TOKEN,token,expire,response);

        return new ModelAndView("redirect:/seller/index");
    }

    /**
     * 卖家用户绑定微信扫码登录
     * @param openId    用户用于扫码登录的openId
     */
    @GetMapping("bindQrLogin/{sellerId}")
    public ModelAndView bindQrLogin(@PathVariable("sellerId") Integer sellerId,
                                    @RequestParam("openId") String openId){
        //查找该用户
        Seller seller = sellerService.findBySellerId(sellerId);
        if (seller == null){
            throw new SellException(ResultEnum.USER_NOT_EXIST_OR_AUTH);
        }

        //设置openId
        seller.setOpenId(openId);
        //保存至数据库
        sellerService.save(seller);

        ModelAndView mv = new ModelAndView();
        mv.addObject("message",ResultEnum.QRLOGIN_BIND_SUCCEED.getMessage());
        mv.setViewName("shop/login");

        return mv;
    }

    //登出  清除cookie和redis信息
    @GetMapping("logout")
    public ModelAndView logout (HttpServletRequest request,
                                HttpServletResponse response,
                                Map<String,Object> map){
        //获取cookie
        Cookie cookie = CookieUtil.getCookie(CookieConst.TOKEN,request);
        if (cookie != null){
            //清除redis中的健值
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConst.TOKEN_PREFIX,cookie.getValue()) );
            //清除cookie中的token
            CookieUtil.setCookie(CookieConst.TOKEN,null,0, response);
        }

        map.put("message",ResultEnum.LOGOUT_SUCCESS.getMessage());

        return new ModelAndView("shop/login",map);
    }

    //管理员用户查看个人信息
    @GetMapping("profile/{sellerId}")
    public ModelAndView profile(@PathVariable("sellerId") Integer sellerId){

        Seller seller = sellerService.findBySellerId(sellerId);

        //使用sellerVO 去掉不宜展示的信息
        SellerVO sellerVO = new SellerVO();
        BeanUtils.copyProperties(seller,sellerVO);

        //获取用户权限信息
        String privilege = SellerAdminRightsEnum.getMessageByCode(sellerVO.getPrivilege());

        ModelAndView mv = new ModelAndView();
        mv.addObject("sellerVO",sellerVO);
        mv.addObject("privilege",privilege);
        mv.setViewName("shop/profile");

        return mv;
    }

    //返回修改密码视图页面
    @GetMapping("password/{sellerId}")
    public ModelAndView password(@PathVariable("sellerId") Integer sellerId){

        ModelAndView mv = new ModelAndView();
        mv.addObject("sellerId",sellerId);
        mv.setViewName("shop/admin-password");

        return mv;
    }

    //管理员用户更新密码
    @PostMapping("changePassword")
    @ResponseBody
    public Result<Object> changePassword(@Valid PasswordForm passwordForm,
                                         BindingResult bindingResult,
                                         HttpServletRequest request){
        //校验参数
        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                            Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        //获取前置切面请求中的用户信息
        SellerVO sellerVO = (SellerVO) request.getAttribute("sellerVO");
        //校验当前用户和要修改的用户是否一致 防止越权
        if (!sellerVO.getSellerId().equals(passwordForm.getId())){
            throw new SellException(ResultEnum.ILLEGAL_OPERATION);
        }

        //更新密码
        Seller seller = sellerService.updatePassword(passwordForm);

        if (seller == null){
            throw new SellException(ResultEnum.UPDATE_PASSWORD_FAIL);
        }

        return ResultUtil.success();
    }


}
