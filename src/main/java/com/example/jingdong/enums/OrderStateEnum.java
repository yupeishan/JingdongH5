package com.example.jingdong.enums;

public enum OrderStateEnum implements BaseEnum<Object>{

    ORDER_WAIT_CONFIRM(0,"待确认"),
    ORDER_CANCEL(1,"已取消"),
    ORDER_REFUNDED(2,"已退款"),
    ORDER_SHIPPED_NO(3,"待商家发货"),
    ORDER_SHIPPED_YES(4,"运输中"),
    ORDER_HAS_BEEN_DELIVERED(5,"已送达，待确认"),
    ORDER_FINISHED(6,"已完成"),
    ;

    private final Integer code;
    private final String message;

    OrderStateEnum(Integer code, String message){
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
