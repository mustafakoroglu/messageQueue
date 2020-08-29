package com.message.manager.messageSender;

import com.message.dto.MessageDTO;
import com.message.manager.messageSender.ifc.MessageSenderIfc;

public class AbstractMessageSender implements MessageSenderIfc,Runnable {

    public MessageDTO messageDTO;

    public AbstractMessageSender(MessageDTO messageDTO){
        this.messageDTO = messageDTO;
    }

    public void send(){
        send(messageDTO);
    }

    public void send(MessageDTO messageDTO){
        System.out.println("--MESSAGE SENT: " +messageDTO);
    }

    public void run(){
        send();
    }

}
