package com.message.manager.messageSender;

import com.message.dto.MessageDTO;
import com.message.common.enums.ActionTypeEnum;

public class MessageSenderFactory {

    public AbstractMessageSender getMessageSender(MessageDTO messageDTO){
        ActionTypeEnum actionType = messageDTO.getActionType();
        if(actionType.equals(ActionTypeEnum.SMS)){
            return new SmsSenderManager(messageDTO);
        }
        else if(actionType.equals(ActionTypeEnum.EMAIL)){
            return new EmailSenderManager(messageDTO);
        }
        else if(actionType.equals(ActionTypeEnum.PUSH)){
            return new PushSenderManager(messageDTO);
        }
        return null;
    }
}
