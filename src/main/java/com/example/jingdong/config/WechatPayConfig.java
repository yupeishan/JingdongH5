package com.example.jingdong.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatPayConfig{

    @Autowired
    private WechatAccountConfig config;

    /**
     * 构建微信支付service
     * @return WxPayService
     */
    @Bean
    public WxPayService wxPayService(){
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig());
        return wxPayService;
    }


    /**
     * 获取初始化配置
     * @return WxPayConfig
     */
    public WxPayConfig wxPayConfig(){
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(config.getMpAppId());
        payConfig.setMchId(config.getMchId());
        payConfig.setMchKey(config.getMchKey());
        payConfig.setKeyPath(config.getKeyPath());
        payConfig.setNotifyUrl(config.getNotifyUrl());

        return payConfig;
    }


}
