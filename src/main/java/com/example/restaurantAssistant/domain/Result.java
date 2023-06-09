package com.example.restaurantAssistant.domain;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;
    private String message;
    private Object data;

}
