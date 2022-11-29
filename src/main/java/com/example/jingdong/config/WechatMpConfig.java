package com.example.jingdong.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatMpConfig {

    @Autowired
    private WechatAccountConfig config;

    /**
     * 使用配置类对象初始化wxMpService
     * @return WxMpService
     */
    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        //使用配置类初始化wxMpService
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }


    /**
     * 设置初始化配置信息 AppId AppSecret
     * @return wxMpDefaultConfig 配置类对象
     */
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId(config.getMpAppId());
        wxMpDefaultConfig.setSecret(config.getMpAppSecret());

        return wxMpDefaultConfig;
    }



}
