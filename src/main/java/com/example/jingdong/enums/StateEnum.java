package com.example.jingdong.enums;

public enum StateEnum implements BaseEnum<Object>{

    //是否是推荐商品
    RECOMMEND_NO(0,"正常"),
    RECOMMEND_YES(1,"推荐"),

    //状态 正常或禁用
    STATE_NO(0,"审核"),
    STATE_YES(1,"正常"),

    //是否是默认地址
    DEFAULT_ADDR_NO(0,"普通地址"),
    DEFAULT_ADDR_YES(1,"默认地址"),
    ;

    private final Integer code;
    private final String message;

    StateEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }


    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }



}
