package com.example.restaurantAssistant.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yg
 */
@Data
public class DinnerDTO implements Serializable {
    private static final long serialVersionUID = -2045165728176239872L;

    private Integer id;
    private Integer userId;
    private Integer seatId;
    /**
     * 用餐状态：0未使用 1使用中 2已使用 3已取消
     */
    private Integer status;
    private Date dinnerTime;
}
