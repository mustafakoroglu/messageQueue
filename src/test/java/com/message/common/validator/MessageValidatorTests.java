package com.message.common.validator;


import com.message.common.enums.ActionTypeEnum;
import com.message.common.enums.ExceptionMessageEnum;
import com.message.common.exception.BusinessException;
import com.message.common.validator.MessageValidator;
import com.message.dto.MessageDTO;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("messageDTO validator tests")
public class MessageValidatorTests {

    ActionTypeEnum sms = ActionTypeEnum.SMS;
    ActionTypeEnum email = ActionTypeEnum.EMAIL;
    ActionTypeEnum push = ActionTypeEnum.PUSH;

    String numeric = "123123123";
    String alphaNumericPhoneNo = "+213123123";
    String alphanumeric = "23123sadasd";
    String notAlphaNumeric = "invalid|";

    String emailValid = "test@tes.com";


    @Test
    void validateMessage_invalid(){
        final MessageDTO nullMsg = null;
        assertThrows(BusinessException.class, ()-> MessageValidator.validateMessage(nullMsg));
        MessageDTO nullAction = new MessageDTO();
        assertThrows(BusinessException.class, ()-> MessageValidator.validateMessage(nullAction));
        MessageDTO blankText = new MessageDTO();
        blankText.setActionType(ActionTypeEnum.SMS);
        blankText.setText("  ");
        BusinessException ex = assertThrows(BusinessException.class, ()-> MessageValidator.validateMessage(blankText));
        assertSame(ExceptionMessageEnum.TEXT_BLANK.getCode(),ex.getCode());
    }

    @Test
    void validateConsumerAddress_test_sms_valid(){
        assertDoesNotThrow(() ->MessageValidator.validateCustomerAddress(numeric,sms));
        assertDoesNotThrow(() ->MessageValidator.validateCustomerAddress(alphaNumericPhoneNo,sms));
    }

    @Test
    void validateConsumerAddress_test_sms_invalid(){
        assertThrows(BusinessException.class, () ->MessageValidator.validateCustomerAddress(alphanumeric,sms));
        assertThrows(BusinessException.class, () ->MessageValidator.validateCustomerAddress(notAlphaNumeric,sms));
        assertThrows(BusinessException.class, () ->MessageValidator.validateCustomerAddress(emailValid,sms));
    }


    @Test
    void validateConsumerAddress_test_email_valid(){
        assertDoesNotThrow(() ->MessageValidator.validateCustomerAddress(emailValid,email));
    }

    @Test
    void validateConsumerAddress_test_email_invalid(){
        assertThrows(BusinessException.class, () ->MessageValidator.validateCustomerAddress(numeric,email));
        assertThrows(BusinessException.class, () ->MessageValidator.validateCustomerAddress(alphanumeric,email));
        assertThrows(BusinessException.class, () ->MessageValidator.validateCustomerAddress(notAlphaNumeric,email));
    }

    @Test
    void validateConsumerAddress_test_push_valid(){
        assertDoesNotThrow(() ->MessageValidator.validateCustomerAddress(numeric,push));
        assertDoesNotThrow(() ->MessageValidator.validateCustomerAddress(alphanumeric,push));
    }

    @Test
    void validateConsumerAddress_test_push_invalid(){
        assertThrows(BusinessException.class, () ->MessageValidator.validateCustomerAddress(notAlphaNumeric,push));
        assertThrows(BusinessException.class, () ->MessageValidator.validateCustomerAddress(emailValid,push));
    }







}
