package com.message.manager.messageSender.ifc;

import com.message.dto.MessageDTO;

public interface MessageSenderIfc {

    void send(MessageDTO messageDTO);

}
