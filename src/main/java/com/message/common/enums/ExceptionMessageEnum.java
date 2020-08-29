package com.message.common.enums;

public enum ExceptionMessageEnum {

    MESSAGE_NULL(1,"Message is null"),
    CUSTOMER_ADDRESS_BLANK(2,"Customer Address is blank"),
    TEXT_BLANK(3,"Message text blank"),
    ACTION_TYPE_NULL(4,"Action type is null"),
    CELL_PHONE_NO_NOT_VALID(5,"Cell phone number is not valid"),
    EMAIL_ADDRESS_NOT_VALID(6,"Email address number is not valid"),
    MOBILE_CELL_ID_NOT_VALID(7,"Mobile cell id is not valid"),
    TECH_EXP_PROPS_NOT_LOADED(90,"properties cannot be loaded. application does not be started.");

    int code;
    String message;

    ExceptionMessageEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode(){
        return code;
    }

    public String toString(){
        return "Business Exception: " + code + " - " + message;
    }
}
