package com.example.restaurantAssistant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yg
 */
@Getter
@AllArgsConstructor
public enum DinnerStateEnum implements BaseEnum<Object> {

    NOT_USED(0, "NOT_USED"),

    IN_USE(1, "IN_USE"),

    USED(2, "USED"),
    CANCEL(3, "CANCEL");

    private final Integer code;
    private final String message;

}
