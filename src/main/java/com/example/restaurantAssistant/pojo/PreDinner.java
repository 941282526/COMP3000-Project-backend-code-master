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
import java.util.Date;

@Entity
@Data
@Table( name = "pre_dinner" )
@Proxy(lazy = false)
@DynamicInsert
@DynamicUpdate
public class PreDinner {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer seatId;
    /**
     * 用餐状态：0未使用 1使用中 2已使用 3已取消
     */
    private Integer status;
    private Date dinnerTime;
    private String createTime;
    private String updateTime;
}
