package com.message.common.validator;

import com.message.common.enums.ExceptionMessageEnum;
import com.message.common.exception.TechnicalException;
import com.message.common.util.ConstantsIfc;

import java.util.Properties;

public class PropertyValidator {

    public static void validateProperties(Properties props){
        try{
            int smsMaxTread = Integer.parseInt(props.getProperty(ConstantsIfc.SMS_MAX_THREAD_SIZE));
            int emailMaxTread = Integer.parseInt(props.getProperty(ConstantsIfc.EMAIL_MAX_THREAD_SIZE));
            int pushMaxTread = Integer.parseInt(props.getProperty(ConstantsIfc.PUSH_MAX_THREAD_SIZE));
            if(smsMaxTread < 1 || emailMaxTread < 1 || pushMaxTread < 1){
                throw new Exception();
            }
        }
        catch (Exception e){ //can be thrown <1 exception || NumberFormatException
            TechnicalException.throwException(ExceptionMessageEnum.TECH_EXP_PROPS_NOT_LOADED);
        }
    }
}
