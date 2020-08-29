package com.message.common.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.dto.MessageDTO;

public class MessageDeserializer implements org.apache.kafka.common.serialization.Deserializer<MessageDTO> {

    public MessageDTO deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        MessageDTO obj = null;
        try {
            obj = mapper.readValue(arg1, MessageDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void close() {
    }

}
