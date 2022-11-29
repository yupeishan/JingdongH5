package com.example.jingdong.exception.handler;

import com.example.jingdong.domain.Result;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SellExceptionHandler {


    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public <T> Result<T> exceptionHandler(SellException e){
        return ResultUtil.error(e.getCode(),e.getMessage());
    }

}
