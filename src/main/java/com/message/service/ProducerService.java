package com.message.service;

import com.message.dto.MessageDTO;
import com.message.common.exception.BusinessException;
import com.message.manager.producer.AbstractProducerManager;
import com.message.manager.producer.InMemoryQueueProducerManager;
import com.message.manager.producer.KafkaProducerManager;
import com.message.common.util.ConstantsIfc;
import com.message.common.util.Utils;

import java.util.Properties;

public class ProducerService {

    private static AbstractProducerManager manager = null;

    public boolean addMessage(MessageDTO messageDTO) throws BusinessException {
        return getProducerManager().addMessage(messageDTO);
    }

    private AbstractProducerManager getProducerManager(){
        if(manager == null){
            Properties props = Utils.loadProperties();
            if(Utils.getBooleanProperty(props, ConstantsIfc.IS_KAFKA_QUEUE)){
                manager = new KafkaProducerManager();
            }
            else{
                manager = new InMemoryQueueProducerManager();
            }
        }
        return manager;
    }
}
