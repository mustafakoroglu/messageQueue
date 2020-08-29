package com.message.common.util;


import com.message.common.enums.ActionTypeEnum;
import com.message.common.exception.BusinessException;
import com.message.common.validator.MessageValidator;
import org.junit.jupiter.api.*;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("util class tests")
public class UtilsTests {

    Properties prop;
    @BeforeAll
    void initProp(){
        prop = new Properties();
        prop.setProperty("trueProp","true");
        prop.setProperty("trueProp2","TRue");
        prop.setProperty("notTrue","anyStrNotTrue");

    }

    @Test
    void getBooleanProperty_test(){
        assertTrue(() ->Utils.getBooleanProperty(prop,"trueProp"));
        assertTrue(() ->Utils.getBooleanProperty(prop,"trueProp2"));
        assertFalse(() ->Utils.getBooleanProperty(prop,"notTrue"));
    }



}
