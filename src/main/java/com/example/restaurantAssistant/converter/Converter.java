package com.example.restaurantAssistant.converter;

import com.alibaba.fastjson.JSONObject;
import com.example.restaurantAssistant.dto.OrderDTO;
import com.example.restaurantAssistant.form.OrderForm;
import com.example.restaurantAssistant.pojo.OrderDetail;
import org.springframework.beans.BeanUtils;

public class Converter {


    //将用户创建订单提交的orderForm转换为orderDTO
    public static OrderDTO OrderFormToOrderDTO(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();

        BeanUtils.copyProperties(orderForm,orderDTO);

        //将JSON转换为List<orderDetail> 赋值给orderDTO
        orderDTO.setOrderDetailList(JSONObject.parseArray(orderForm.getProducts(),OrderDetail.class));

        return orderDTO;
    }


}
