package com.example.jingdong.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatOpenConfig {

    @Autowired
    private WechatAccountConfig config;

    /**
     * 使用配置类对象初始化wxMpService
     * @return WxMpService
     */
    @Bean(name = "wxOpenService")
    public WxMpService getWxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        //使用配置类初始化wxMpService
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }


    /**
     * 初始化配置类信息
     * openAppId        微信开放平台应用id
     * openAppSecret    应用秘钥
     * @return          openAppSecret 配置类对象
     */
    private WxMpConfigStorage wxMpConfigStorage(){
        WxMpDefaultConfigImpl wxMpConfig = new WxMpDefaultConfigImpl();
        wxMpConfig.setAppId(config.getOpenAppId());
        wxMpConfig.setSecret(config.getOpenAppSecret());

        return wxMpConfig;
    }







}
