package com.example.jingdong.controller.common;

import com.example.jingdong.config.WechatAccountConfig;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private WechatAccountConfig config;

    /**
     * 访问微信授权连接 获取code 跳转userInfo方法获取token
     *
     * @param returnUrl 授权成功后跳转的页面
     * @return 跳转userInfo方法获取token
     */
    @GetMapping("auth")
    public String auth(@RequestParam("returnUrl") String returnUrl)
                                        throws UnsupportedEncodingException {
        String redirectUrl = wxMpService.getOAuth2Service().buildAuthorizationUrl(
                config.getMpUserInfoUrl(),
                WxConsts.OAuth2Scope.SNSAPI_USERINFO,
                URLEncoder.encode(returnUrl, "utf-8"));

        return "redirect:" + redirectUrl;
    }


    /**
     * 成功获取code后 微信通过回调地址跳转userInfo方法
     *
     * @param code      获取到的code
     * @param returnUrl auth通过state传过来的链接 授权成功后跳转该链接
     * @return 携带openid跳转到returnUrl
     */
    @GetMapping("userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {

        WxOAuth2AccessToken token;
        WxOAuth2UserInfo wxUserInfo;
        try {
            //通过获取到的code换取token
            token = wxMpService.getOAuth2Service().getAccessToken(code);
            //通过token获取用户信息
            wxUserInfo = wxMpService.getOAuth2Service().getUserInfo(token, null);
        } catch (WxErrorException e) {
            log.info("授权失败 原因={}",e.getMessage());
            throw new SellException(ResultEnum.WECHAT_AUTH_FAIL);
        }
        log.info("openid={}", wxUserInfo.getOpenid());
        log.info("wxUserInfo={}", wxUserInfo);

        //授权成功携带用户信息跳转至回调链接
        return "redirect:" + returnUrl + "?openId=" + wxUserInfo.getOpenid();
    }


    /**
     * 商家扫码登录接口
     *
     * @param returnUrl 成功后跳转的地址
     * @return 跳转获取用户信息的方法
     */
    @GetMapping("qrAuth")
    public String qrAuth(@RequestParam("returnUrl") String returnUrl)
            throws UnsupportedEncodingException {
        //获取用户信息的回调函数
        String url = config.getOpenUserInfoUrl();
        //构造扫码登录请求链接
        String redirectUrl = wxOpenService.buildQrConnectUrl(url,
                WxConsts.QrConnectScope.SNSAPI_LOGIN,
                URLEncoder.encode(returnUrl, "utf-8"));

        return "redirect:" + redirectUrl;
    }

    //商家扫码登录 获取信息回调接口
    @GetMapping("qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl) {
        WxOAuth2AccessToken wxOAuth2AccessToken;
        log.info("code={}", code);
        log.info("returnUrl={}", returnUrl);
        try {
            //通过获取到的code换取token
            wxOAuth2AccessToken = wxOpenService.getOAuth2Service().getAccessToken(code);
            log.info("wxOAuth2AccessToken={}", wxOAuth2AccessToken);
        } catch (WxErrorException e) {
            throw new SellException(ResultEnum.WECHAT_AUTH_FAIL.getCode(), e.getMessage());
        }

        //通过token获取openId
        String openId = wxOAuth2AccessToken.getOpenId();
        log.info("openId={}", openId);

        //授权成功携带openid跳转至回调地址
        return "redirect:" + returnUrl + "?openId=" + openId;
    }


}
