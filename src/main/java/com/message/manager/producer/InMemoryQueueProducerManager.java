package com.message.manager.producer;

import com.message.dto.MessageDTO;
import com.message.common.exception.BusinessException;
import com.message.manager.ifc.ProducerManagerIfc;

import java.util.concurrent.ConcurrentLinkedQueue;

public class InMemoryQueueProducerManager extends AbstractProducerManager implements ProducerManagerIfc {

    public static final ConcurrentLinkedQueue<MessageDTO> QUEUE = new ConcurrentLinkedQueue();

    public boolean addMessage(MessageDTO messageDTO) throws BusinessException{
        super.addMessage(messageDTO);
        QUEUE.add(messageDTO);
        return true;
    }




}
