package com.example.restaurantAssistant.exception;

import com.example.restaurantAssistant.enums.ResultEnum;

public class SellException extends RuntimeException{

    private final Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code ,String message){
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
