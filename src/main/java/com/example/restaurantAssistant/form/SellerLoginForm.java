package com.example.restaurantAssistant.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SellerLoginForm {


    @NotNull( message = "enter one user name")
    @Size(min = 6,max = 18,message = "The username length must be between 6 and 18")
    private String username;

    @NotNull( message = "Please input a password")
    @Size( min = 6 ,max = 18,message = "Password length needs to be between 8 and 20")
    private String password;

//    @NotNull( message = "请输入验证码")
//    private String code;


}
