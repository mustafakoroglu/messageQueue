package com.message.common.validator;


import com.message.common.enums.ActionTypeEnum;
import com.message.common.exception.BusinessException;
import com.message.common.exception.TechnicalException;
import com.message.common.util.ConstantsIfc;
import com.message.common.validator.MessageValidator;
import com.message.common.validator.PropertyValidator;
import org.junit.jupiter.api.*;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("property validator tests")
public class PropertyValidatorTests {

    String notNumeric = "sdasd";
    String numeric = "2112";
    String negativeNumStr = "-1231";

    @Test
    void validateProperties_test_empty(){
        Properties prop = new Properties();
        assertThrows(TechnicalException.class,() ->PropertyValidator.validateProperties(prop));
    }

    @Test
    void validateProperties_test_invalid(){
        validateProperties_invalid(ConstantsIfc.SMS_MAX_THREAD_SIZE);
        validateProperties_invalid(ConstantsIfc.EMAIL_MAX_THREAD_SIZE);
        validateProperties_invalid(ConstantsIfc.PUSH_MAX_THREAD_SIZE);
    }

    void validateProperties_invalid(String type){
        Properties prop = new Properties();
        prop.setProperty(type,notNumeric);
        assertThrows(TechnicalException.class,() ->PropertyValidator.validateProperties(prop));
        prop.setProperty(type,negativeNumStr);
        assertThrows(TechnicalException.class,() ->PropertyValidator.validateProperties(prop));
    }

    @Test
    void validateProperties_test_valid(){
        Properties prop = new Properties();
        prop.setProperty(ConstantsIfc.SMS_MAX_THREAD_SIZE,numeric);
        prop.setProperty(ConstantsIfc.EMAIL_MAX_THREAD_SIZE,numeric);
        prop.setProperty(ConstantsIfc.PUSH_MAX_THREAD_SIZE,numeric);
        assertDoesNotThrow(() ->PropertyValidator.validateProperties(prop));
    }








}
