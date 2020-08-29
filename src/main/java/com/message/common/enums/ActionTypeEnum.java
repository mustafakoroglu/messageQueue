package com.message.common.enums;

public enum ActionTypeEnum {

    SMS("SMS"),
    EMAIL("EMAIL"),
    PUSH("PUSH");

    String type;

    ActionTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String toString(){
        return getType();
    }
}
