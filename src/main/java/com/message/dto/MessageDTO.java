package com.message.dto;

import com.message.common.enums.ActionTypeEnum;

import java.io.Serializable;

public class MessageDTO implements Serializable {

    private String customerAddress;
    private ActionTypeEnum actionType;
    private String text;

    public MessageDTO(){

    }

    public MessageDTO(String customerAddress, ActionTypeEnum actionType, String text) {
        this.customerAddress = customerAddress;
        this.actionType = actionType;
        this.text = text;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public ActionTypeEnum getActionType() {
        return actionType;
    }

    public void setActionType(ActionTypeEnum actionType) {
        this.actionType = actionType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString(){
        return "actionType= "+ actionType + "   address= " +customerAddress + "     text= " + text;
    }
}
