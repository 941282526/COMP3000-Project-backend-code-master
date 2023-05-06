package com.example.jingdong.enums;

public enum PayStateEnum implements BaseEnum<Object>{

    PAY_NOT(0,"Waiting_Place_Order"),
    PAY_YES(1,"Complete_order")
    ;

    private final Integer code;
    private final String message;

    PayStateEnum(Integer code, String message){
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
