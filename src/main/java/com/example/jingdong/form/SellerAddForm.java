package com.example.jingdong.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//用于更新店铺管理员数据
@Data
public class SellerAddForm {

    @NotNull(message = "User name cannot be empty")
    @Size(min = 6,max = 18,message = "The username length must be between 6 and 18")
    private String username;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 6,max = 18,message = "The username length must be between 6 and 18")
    private String password;

    @NotNull(message = "Duplicate password cannot be empty")
    @Size(min = 6,max = 18,message = "The username length must be between 6 and 18")
    private String rePassword;

    @NotNull(message = "The real name of the administrator cannot be empty")
    private String realName;

    @NotNull(message = "The administrator permission level cannot be empty")
    private Integer privilege;

    @NotNull(message = "The administrator contact email cannot be empty")
    private String email;

}
