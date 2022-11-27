package com.example.jingdong.controller.common;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @Autowired
    private RestTemplate restTemplate;

    private final String appid = "wx680e2c1d87e8226c";
    private final String secret = "913842a25888610d49be5e0a3abd91db";
    private final String redirect_uri = "http://dthhzh.natappfree.cc/weixin/userInfo";
    private final String scope = "snsapi_userinfo";

    @GetMapping("auth")
    public String auth(){
        String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                                +appid+"&redirect_uri="
                                +redirect_uri+"&response_type=code&scope="
                                +scope+"&state=STATE#wechat_redirect";
        log.info("redirectUrl={}",redirectUrl);
        return "redirect:"+redirectUrl;
    }


    @GetMapping("userInfo")
    @ResponseBody
    public JSONObject userInfo(@RequestParam("code") String code){
        log.info("code={}",code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                        +appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";

        String result = restTemplate.getForObject(url,String.class);
        JSONObject jsonObject = JSONObject.parseObject(result);
        log.info("jsonObject={}",jsonObject);
        assert jsonObject != null;
        jsonObject = getUserInfo(jsonObject.getString("access_token"), jsonObject.getString("openid"));
        return jsonObject;
    }


    private JSONObject getUserInfo(String accessToken,String openId){
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="
                        +accessToken+"&openid="+openId+"&lang=zh_CN";
        String result = restTemplate.getForObject(url,String.class);
        log.info(result);
        return JSONObject.parseObject(result);
    }

}
