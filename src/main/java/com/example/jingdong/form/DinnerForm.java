package com.example.jingdong.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yg
 */
@Data
public class DinnerForm implements Serializable {

    private static final long serialVersionUID = 2962643921857716436L;

    private Integer userId;
    private Integer seatId;
    private Integer quantity;
    @NotNull(message = "The ordering time cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dinnerTime;
}
