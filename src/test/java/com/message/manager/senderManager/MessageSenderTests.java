package com.message.manager.senderManager;


import com.message.common.enums.ActionTypeEnum;
import com.message.common.exception.TechnicalException;
import com.message.common.util.ConstantsIfc;
import com.message.common.validator.PropertyValidator;
import com.message.dto.MessageDTO;
import com.message.manager.messageSender.EmailSenderManager;
import com.message.manager.messageSender.MessageSenderFactory;
import com.message.manager.messageSender.PushSenderManager;
import com.message.manager.messageSender.SmsSenderManager;
import com.message.manager.messageSender.ifc.MessageSenderIfc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("message Sender tests")
public class MessageSenderTests {

   MessageDTO messageDTO;

   @BeforeEach
   void initMessage(){
       messageDTO = new MessageDTO();
       messageDTO.setText("text");
   }

   //TODO: will be added
    @Test
    void sendSms_tests(){
       messageDTO.setActionType(ActionTypeEnum.SMS);
        assertDoesNotThrow(()->new SmsSenderManager(messageDTO).send());
    }

    //TODO: will be added
    @Test
    void sendEmail_tests(){
        messageDTO.setActionType(ActionTypeEnum.EMAIL);
        assertDoesNotThrow(()->new EmailSenderManager(messageDTO).send());
    }

    //TODO: will be added
    @Test
    void sendPush_tests(){
        messageDTO.setActionType(ActionTypeEnum.PUSH);
        assertDoesNotThrow(()->new PushSenderManager(messageDTO).send());
    }

    @Test
    void messageSenderFactory_tests(){
        MessageSenderFactory factory = new MessageSenderFactory();
        messageDTO.setActionType(ActionTypeEnum.SMS);
        assertTrue(factory.getMessageSender(messageDTO) instanceof SmsSenderManager);
        messageDTO.setActionType(ActionTypeEnum.EMAIL);
        assertTrue(factory.getMessageSender(messageDTO) instanceof EmailSenderManager);
        messageDTO.setActionType(ActionTypeEnum.PUSH);
        assertTrue(factory.getMessageSender(messageDTO) instanceof PushSenderManager);
    }








}
