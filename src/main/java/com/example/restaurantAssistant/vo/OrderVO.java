package com.example.restaurantAssistant.vo;

import com.example.restaurantAssistant.enums.OrderStateEnum;
import com.example.restaurantAssistant.enums.PayStateEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderVO {

    private String orderNumber;
    private String transactionNumber;
    private String consigneeName;
    private String tel;
    private String address;
    private String courierNumber;
    private BigDecimal orderAmount;
    private Integer payState = PayStateEnum.PAY_NOT.getCode();
    private Integer orderState = OrderStateEnum.ORDER_WAIT_CONFIRM.getCode();
    private String payTime;
    private String createTime;
    private String updateTime;


}
