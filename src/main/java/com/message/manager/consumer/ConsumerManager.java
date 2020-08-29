package com.message.manager.consumer;

import com.message.common.exception.BusinessException;
import com.message.common.validator.MessageValidator;
import com.message.dto.MessageDTO;
import com.message.common.enums.ActionTypeEnum;
import com.message.manager.ifc.ConsumerManagerIfc;
import com.message.manager.messageSender.MessageSenderFactory;
import com.message.manager.producer.InMemoryQueueProducerManager;
import com.message.common.util.ConstantsIfc;
import com.message.common.util.Utils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.*;

public class ConsumerManager implements ConsumerManagerIfc,ConstantsIfc {

    ExecutorService smsExecutor;
    ExecutorService emailExecutor;
    ExecutorService pushExecutor;



    public void initMessageSenderConsumer(){

        Properties props = Utils.loadProperties();

        smsExecutor = new ThreadPoolExecutor(0, Utils.getIntegerProperty(props,SMS_MAX_THREAD_SIZE),
                60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new BasicThreadFactory.Builder().namingPattern("SMS-THREAD-POOL-%d").build(),new ThreadPoolExecutor.CallerRunsPolicy());
        emailExecutor = new ThreadPoolExecutor(0, Utils.getIntegerProperty(props,EMAIL_MAX_THREAD_SIZE),
                60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new BasicThreadFactory.Builder().namingPattern("EMAIL-THREAD-POOL-%d").build(),new ThreadPoolExecutor.CallerRunsPolicy());
        pushExecutor = new ThreadPoolExecutor(0, Utils.getIntegerProperty(props,PUSH_MAX_THREAD_SIZE),
                60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new BasicThreadFactory.Builder().namingPattern("PUSH-THREAD-POOL-%d").build(),new ThreadPoolExecutor.CallerRunsPolicy());


        if(Utils.getBooleanProperty(props,IS_KAFKA_QUEUE)){
            startKafkaConsumer();
        }
        else{
            startInMemoryQueueConsumer();
        }
    }

    private void startKafkaConsumer(){

        Properties kafkaProperties = Utils.loadProperties("kafka.consumer.properties");
        KafkaConsumer<String,MessageDTO> consumer = new KafkaConsumer<String, MessageDTO>(kafkaProperties);
        consumer.subscribe(Arrays.asList(ConstantsIfc.KAFKA_TOPIC));

        while(true){
            ConsumerRecords<String,MessageDTO> records = consumer.poll(100);
            for(ConsumerRecord<String,MessageDTO> record : records){
                try{
                    MessageValidator.validateMessage(record.value());
                    sendMessageToExecutor(record.value());
                }
                catch (BusinessException ex){
                    //TODO: log exception
                }
            }
            if(records.isEmpty()){
                Utils.delay(10);
            }
        }
    }

    private void startInMemoryQueueConsumer(){
        while(true){
            if(!InMemoryQueueProducerManager.QUEUE.isEmpty()){
                MessageDTO messageDTO = InMemoryQueueProducerManager.QUEUE.poll();
                try{
                    MessageValidator.validateMessage(messageDTO);
                    sendMessageToExecutor(messageDTO);
                }
                catch (BusinessException ex){
                    //TODO: log exception
                }
            }
            else{
                Utils.delay(10);
            }
        }
    }

    private void sendMessageToExecutor(MessageDTO messageDTO){
        if(messageDTO.getActionType().equals(ActionTypeEnum.SMS))
            smsExecutor.submit(new MessageSenderFactory().getMessageSender(messageDTO));
        else if(messageDTO.getActionType().equals(ActionTypeEnum.EMAIL))
            emailExecutor.submit(new MessageSenderFactory().getMessageSender(messageDTO));
        else
            pushExecutor.submit(new MessageSenderFactory().getMessageSender(messageDTO));
    }


}
