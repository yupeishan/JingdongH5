package com.example.jingdong.constant;

public class RedisConst {

    public static final String TOKEN_PREFIX = "token_%s";

    //生命周期 2小时 卖家后台登录过期时间
    public static final Integer EXPIRE = 7200;

    //生命周期 7天 用户前台登录过期时间
    public static final Integer USER_EXPIRE = 7*24*60*60;

}
