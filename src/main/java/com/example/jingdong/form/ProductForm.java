package com.example.jingdong.form;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductForm {

    private Integer id;

    @NotNull(message = "The product name cannot be empty")
    private String name;

    @NotNull(message = "The sales price of the product cannot be empty")
    @DecimalMin(value = "0.1",message = "The sales price cannot be less than 0.1")
    private BigDecimal price;

    @NotNull(message = "The original price of the product cannot be empty")
    @DecimalMin(value = "0.1",message = "The original price cannot be less than 0.1")
    private BigDecimal oldPrice;

    @NotNull(message = "Product inventory cannot be empty")
    @Min(value = 0,message = "Inventory cannot be less than 0")
    private Integer stock;

    @NotNull(message = "Product type cannot be empty")
    private String type;

    private Integer state;

}
