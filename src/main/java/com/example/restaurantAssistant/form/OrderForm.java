package com.example.restaurantAssistant.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderForm {

    @Min(message = "user id error",value = 1)
    @NotNull(message = "The purchaser userId cannot be empty")
    private Integer userId;

    @NotNull(message = "The recipient's name cannot be empty")
    private String consigneeName;

    @NotNull(message = "The recipient's contact information cannot be empty")
    private String tel;

    @NotNull(message = "The recipient's shipping address cannot be empty")
    private String address;

    private String userOpenId;

    @NotNull(message = "Store ID cannot be empty")
    private Integer shopId;

    @NotNull(message = "Store name cannot be empty")
    private String shopName;

    @NotNull(message = "The purchased product cannot be empty")
    private String products;



}
