package com.example.jingdong.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", "no");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        // 图片宽
        properties.setProperty("kaptcha.image.width", "110");
        // 图片高度
        properties.setProperty("kaptcha.image.height", "40");
        //文本集合，验证码值从此集合中获取
        properties.setProperty("kaptcha.textproducer.char.string","23456789abcdefghkmnpqrstuvwxyzABCDEFGHKMNPRSTUVWXYZ");
        //字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        //文字间隔
        properties.setProperty("kaptcha.textproducer.char.space","3");
        //session取值key
        properties.setProperty("kaptcha.session.key", "KAPTCHA_SESSION_KEY");
        //验证码字符串长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        // 这里 是去掉 噪点颜色
        //properties.setProperty("kaptcha.noise.color", "255,96,0");
        // 这是 是图片样式配置 原生的有三种 水纹 、 鱼眼 、 阴影
        // 这里是 我们自己实现的一个 也就是 样式自定义
        properties.setProperty("kaptcha.obscurificator.impl","com.example.jingdong.config.NoWaterRipple");
        // 配置使用原生的 无噪 实现类 NoNoise
        properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }


}
