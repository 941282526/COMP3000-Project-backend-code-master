package com.example.jingdong.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductCategoryForm {

    private Integer id;

    @NotNull(message = "Classification name cannot be empty")
    private String name;

    @NotNull(message = "Classification type cannot be empty")
    private String type;

    private Integer state;

}
