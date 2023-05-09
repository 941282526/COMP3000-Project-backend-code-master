package com.example.restaurantAssistant.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PasswordForm {

    @NotNull(message = "User ID cannot be empty")
    private Integer Id;

    private String oldPassword;

    @NotNull(message = "The new password cannot be empty")
    @Size(min = 6,max = 18,message = "The new password length must be between 6 and 18")
    private String password;

    @NotNull(message = "Duplicate password cannot be empty")
    @Size(min = 6,max = 18,message = "Duplicate password length must be between 6 and 18")
    private String rePassword;

}
