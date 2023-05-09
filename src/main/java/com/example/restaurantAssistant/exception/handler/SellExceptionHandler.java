package com.example.restaurantAssistant.exception.handler;

import com.example.restaurantAssistant.domain.Result;
import com.example.restaurantAssistant.exception.SellException;
import com.example.restaurantAssistant.utils.ResultUtil;
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
