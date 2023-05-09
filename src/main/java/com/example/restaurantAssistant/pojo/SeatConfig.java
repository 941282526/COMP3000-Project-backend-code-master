package com.example.restaurantAssistant.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table( name = "seat_config" )
@Proxy(lazy = false)
@DynamicInsert
@DynamicUpdate
public class SeatConfig {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private String seatType;
    private Integer quantity;
    private String createTime;
    private String updateTime;
}
