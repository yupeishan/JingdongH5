# jingdongH5

#### 介绍
仿京东H5 基于springboot开发的前后端分离项目

github：https://github.com/yupeishan/JingdongH5.git  
码云：https://gitee.com/peishanyu/jingdongH5.git

#### 演示地址
用户端:
请在微信内打开 浏览器无法微信授权登录  
https://www.gaoshiyi.top

商家端:
请在PC端打开 账号 sellerAdmin+店铺编号 密码123456  
如 ‘测试店铺1’  对应后台账号为sellerAdmin1  
扫码登录需配置自己的微信开放平台应用  
https://shop.gaoshiyi.top/seller/index 

#### 技术栈及环境
- IntelliJ IDEA 2022.2.4
- springboot 2.7.3
- JDK 8
- JPA
- redis 5.0
- MySQL 8.0

#### 关于微信支付与授权
1.微信授权登录需要注册自己的微信公众号 没有公众号可以申请一个微信测试号  
申请地址:https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login  
2.申请完成后将配置文件中的公众号ID mpAppId 公众号秘钥 mpAppSecret改称自己的  
并且准备好内网穿透  在测试号页面最底部将回调地址改为自己的内网穿透  
3.微信支付实现需要营业执照 商家端扫码登录需要微信开放平台注册自己的应用  
详情见微信官方文档：https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html  

#### 前端项目代码
github：https://github.com/yupeishan/jingdongH5-vue.git  
gitee： https://gitee.com/peishanyu/jingdongH5-vue.git  

#### 引用
>商家后台基于X-Admin后台管理模板再开发
>https://github.com/xuxuxu-ni/vue-xuAdmin.git

>微信相关使用了binarywang/WXJava
>https://github.com/Wechat-Group/WxJava.git