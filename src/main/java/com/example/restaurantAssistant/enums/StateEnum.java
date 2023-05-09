package com.example.restaurantAssistant.enums;

public enum StateEnum implements BaseEnum<Object>{

    //是否是推荐商品
    RECOMMEND_NO(0,"RECOMMEND_NO"),
    RECOMMEND_YES(1,"RECOMMEND_YES"),

    //状态 正常或禁用
    STATE_NO(0,"STATE_NO"),
    STATE_YES(1,"STATE_YES"),

    //是否是默认地址
    DEFAULT_ADDR_NO(0,"DEFAULT_ADDR_NO"),
    DEFAULT_ADDR_YES(1,"DEFAULT_ADDR_YES"),
    ;

    private final Integer code;
    private final String message;

    StateEnum(Integer code,String message){
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
