package com.example.restaurantAssistant.dto;

import com.example.restaurantAssistant.enums.OrderStateEnum;
import com.example.restaurantAssistant.enums.PayStateEnum;
import com.example.restaurantAssistant.pojo.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {

    private String orderNumber;
    private Integer userId;
    private String consigneeName;
    private String tel;
    private String address;
    private String courierNumber;
    private String userOpenId;
    private Integer shopId;
    private String shopName;
    private BigDecimal orderAmount;
    private String payTime;
    private Integer payState = PayStateEnum.PAY_NOT.getCode();
    private Integer orderState = OrderStateEnum.ORDER_WAIT_CONFIRM.getCode();
    private String createTime;
    private String updateTime;
    private List<OrderDetail> orderDetailList;

}
