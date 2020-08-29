package com.message.manager.messageSender;

import com.message.dto.MessageDTO;
import com.message.common.util.Utils;

public class SmsSenderManager extends AbstractMessageSender {

    public SmsSenderManager(MessageDTO messageDTO){
        super(messageDTO);
    }

    public void send(MessageDTO messageDTO){
        Utils.delay(3);
        super.send(messageDTO);
    }

}
