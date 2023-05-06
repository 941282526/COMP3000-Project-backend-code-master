package com.example.jingdong.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yg
 */
@Getter
@AllArgsConstructor
public enum SeatTypeEnum implements BaseEnum<Object> {

    SMALL_TABLE(1, "Small-Table"),
    MEDIUM_TABLE(2, "Middle-Table"),
    LARGE_TABLE(3, "Big-Table");

    private final Integer code;
    private final String message;

    public static SeatTypeEnum getSeatTypeEnumByCode(Integer code) {
        SeatTypeEnum[] values = SeatTypeEnum.values();
        for (SeatTypeEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
