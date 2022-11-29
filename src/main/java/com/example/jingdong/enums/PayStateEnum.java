package com.example.jingdong.enums;

public enum PayStateEnum implements BaseEnum<Object>{

    PAY_NOT(0,"等待支付"),
    PAY_YES(1,"支付成功")
    ;

    private final Integer code;
    private final String message;

    PayStateEnum(Integer code, String message){
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
