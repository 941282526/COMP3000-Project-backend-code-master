package com.example.restaurantAssistant.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserForm {

    @NotNull(message = "User ID cannot be empty")
    private Integer id;

    @NotNull(message = "User nickname cannot be empty")
    @Size(min = 2,max = 12,message = "Nickname length should be between 2 and 12")
    private String nickname;

    @NotNull(message = "The user's mobile phone number cannot be empty")
    @Size(min = 11,max = 11,message = "Mobile number format error")
    private String tel;

}
