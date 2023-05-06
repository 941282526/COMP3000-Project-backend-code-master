package com.example.jingdong.enums;

public enum SellerAdminRightsEnum implements BaseEnum<Object>{

    SUPER_ADMIN(0,"SUPER_ADMIN"),
    COMMON_ADMIN(1,"COMMON_ADMIN"),

    ;

    private final Integer code;
    private final String message;

    SellerAdminRightsEnum(Integer code, String message){
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

    public static String getMessageByCode(Integer code){
        SellerAdminRightsEnum[] values = SellerAdminRightsEnum.values();
        for (SellerAdminRightsEnum value : values) {
            if (value.getCode().equals(code)){
                return value.getMessage();
            }
        }

        return null;
    }

}
