package com.example.jingdong.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties( prefix = "wechat")
public class WechatAccountConfig {

    /*公众号AppId*/
    private String mpAppId;

    /*公众号秘钥*/
    private String mpAppSecret;

    /*微信授权 获取code后 跳转获取用户信息的回调接口*/
    private String mpUserInfoUrl;

    /*微信支付生成的直连商户号*/
    private String mchId;

    /*支付商户秘钥*/
    private String mchKey;

    /*微信支付证书存储路径*/
    private String keyPath;

    /*微信支付结果异步通知接口*/
    private String notifyUrl;

    /*微信开放平台唯一应用标识*/
    private String openAppId;

    /*应用秘钥*/
    private String openAppSecret;

    /*扫码登录回调接口*/
    private String openUserInfoUrl;

    private String baseUrl;


}
