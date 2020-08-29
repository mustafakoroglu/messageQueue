package com.message.manager.messageSender;

import com.message.dto.MessageDTO;
import com.message.common.util.Utils;

public class PushSenderManager extends AbstractMessageSender {

    public PushSenderManager(MessageDTO messageDTO){
        super(messageDTO);
    }

    public void send(MessageDTO messageDTO){
        Utils.delay(1);
        super.send(messageDTO);
    }
}
