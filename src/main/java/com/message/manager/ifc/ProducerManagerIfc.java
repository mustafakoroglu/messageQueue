package com.message.manager.ifc;

import com.message.dto.MessageDTO;
import com.message.common.exception.BusinessException;

public interface ProducerManagerIfc {

    boolean addMessage(MessageDTO messageDTO) throws BusinessException;
}
