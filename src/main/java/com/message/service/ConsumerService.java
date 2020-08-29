package com.message.service;

import com.message.manager.consumer.ConsumerManager;

public class ConsumerService {

    ConsumerManager manager = null;

    public void initMessageSenderConsumer(){
        getConsumerManager().initMessageSenderConsumer();
    }

    private ConsumerManager getConsumerManager(){
        if(manager == null){
            manager = new ConsumerManager();
        }
        return manager;
    }
}
