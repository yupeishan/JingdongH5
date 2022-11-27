package com.example.jingdong.exception;

/**
 * 管理员权限检查异常类
 * 当查看用户权限不足时 抛出此异常
 */
public class RightsException extends RuntimeException{


    public RightsException(String message){
        super(message);
    }

}
