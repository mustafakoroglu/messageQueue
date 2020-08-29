package com.message.manager.messageSender;

import com.message.dto.MessageDTO;
import com.message.common.util.Utils;

public class EmailSenderManager extends AbstractMessageSender {

    public EmailSenderManager(MessageDTO messageDTO){
        super(messageDTO);
    }

    public void send(MessageDTO messageDTO){
        Utils.delay(7);
        super.send(messageDTO);
    }
}
