package com.example.jingdong.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yg
 */
@Data
public class SeatDTO implements Serializable {
    private static final long serialVersionUID = -5345347992772684162L;

    private Integer key;

    private Boolean status;

    private String desc;
}
