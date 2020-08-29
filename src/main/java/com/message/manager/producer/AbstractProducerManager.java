package com.message.manager.producer;

import com.message.dto.MessageDTO;
import com.message.common.exception.BusinessException;
import com.message.manager.ifc.ProducerManagerIfc;
import com.message.common.validator.MessageValidator;

public class AbstractProducerManager implements ProducerManagerIfc {

    public boolean addMessage(MessageDTO messageDTO) throws BusinessException{
        MessageValidator.validateMessage(messageDTO);
        return true;
    }
}
