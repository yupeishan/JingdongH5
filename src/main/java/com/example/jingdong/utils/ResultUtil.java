package com.example.jingdong.utils;

import com.example.jingdong.domain.Result;
import com.example.jingdong.enums.ResultEnum;

public class ResultUtil {

    //返回错误信息
    public static <T> Result<T> error(Integer code ,String message){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> error(ResultEnum resultEnum){
        Result<T> result = new Result<>();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }

    //成功 有返回数据
    public static <T> Result<T> success(Object data){
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    //成功 无返回数据
    public static <T> Result<T> success(){
        return success(null);
    }







}
