package com.message.manager.producer;

import com.message.dto.MessageDTO;
import com.message.common.exception.BusinessException;
import com.message.manager.ifc.ProducerManagerIfc;
import com.message.common.util.ConstantsIfc;
import com.message.common.util.Utils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerManager extends AbstractProducerManager implements ProducerManagerIfc {

    public static Properties kafkaProperties;

    static{
        kafkaProperties = Utils.loadProperties("kafka.producer.properties");
    }

    public boolean addMessage(MessageDTO messageDTO) throws BusinessException {
        super.addMessage(messageDTO);
        KafkaProducer producer = createKafkaProducer();
        ProducerRecord<String,MessageDTO> record = new ProducerRecord<String, MessageDTO>(ConstantsIfc.KAFKA_TOPIC,messageDTO);
        producer.send(record);
        producer.close();
        return true;
    }

    private static KafkaProducer<String,MessageDTO> createKafkaProducer(){
        return new KafkaProducer(kafkaProperties);
    }


}
