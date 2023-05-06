package com.example.jingdong.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddressForm {

    private Integer id;

    @NotNull( message = "The user ID cannot be empty")
    private Integer userId;

    @NotNull( message = "The recipient's name cannot be empty")
    @Size(min = 2,max = 10,message = "The format of the recipient's name is incorrect")
    private String consigneeName;

    @NotNull( message = "Phone cannot be empty")
    @Size(min = 11,max = 11,message = "Incorrect phone number format")
    private String tel;

    private String area;

    @NotNull( message = "The detailed address cannot be empty")
    private String detailedAddress;

    private Integer isDefault;

}
