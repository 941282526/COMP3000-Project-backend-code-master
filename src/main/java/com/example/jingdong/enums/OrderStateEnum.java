package com.example.jingdong.enums;

public enum OrderStateEnum implements BaseEnum<Object>{

    ORDER_WAIT_CONFIRM(0,"ORDER_WAIT_CONFIRM"),
    ORDER_CANCEL(1,"ORDER_CANCEL"),
    ORDER_REFUNDED(2,"ORDER_REFUNDED"),
    ORDER_SHIPPED_NO(3,"ORDER_SHIPPED_NO"),
    ORDER_SHIPPED_YES(4,"ORDER_SHIPPED_YES"),
    ORDER_HAS_BEEN_DELIVERED(5,"ORDER_HAS_BEEN_DELIVERED"),
    ORDER_FINISHED(6,"ORDER_FINISHED"),
    ;

    private final Integer code;
    private final String message;

    OrderStateEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
