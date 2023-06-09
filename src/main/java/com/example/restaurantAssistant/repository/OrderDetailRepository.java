package com.example.restaurantAssistant.repository;

import com.example.restaurantAssistant.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    //根据订单号查询
    List<OrderDetail> findByOrderNumber(String orderNo);



}
