package com.message.common.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.dto.MessageDTO;

public class MessageSerializer implements org.apache.kafka.common.serialization.Serializer<MessageDTO> {

    public byte[] serialize(String arg0, MessageDTO arg1) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(arg1).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    public void close() {
    }


}
