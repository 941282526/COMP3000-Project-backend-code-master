package com.example.restaurantAssistant.pojo;

import com.example.restaurantAssistant.enums.StateEnum;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table( name = "user" )
@Proxy( lazy = false )
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String openId;
    private String number;
    private String nickname;
    private String tel;
    private String headPortrait;
    private BigDecimal money;
    private Integer coupons;
    private BigDecimal goldCoin;
    private BigDecimal iou;
    @Transient
    private String token;
    private Integer state = StateEnum.STATE_YES.getCode();



}
