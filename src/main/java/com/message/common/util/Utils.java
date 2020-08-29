package com.message.common.util;

import com.message.common.enums.ExceptionMessageEnum;
import com.message.common.exception.TechnicalException;
import com.message.common.validator.PropertyValidator;

import java.io.InputStream;
import java.util.Properties;

public class Utils {

    /*
     * use for sendermanagers simulation
     */
    public static void delay(int sec){
        try{
            Thread.sleep(sec*1000);
        }
        catch (InterruptedException e){
        }
    }

    public static int getIntegerProperty(Properties props, String propKey){
        return Integer.parseInt(props.getProperty(propKey));
    }

    public static boolean getBooleanProperty(Properties props, String propKey){
        return Boolean.parseBoolean(props.getProperty(propKey));
    }

    public static Properties loadProperties(final String fileName){
        InputStream is = null;
        Properties props = new Properties();
        try{
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            is = classloader.getResourceAsStream(fileName);
            props.load(is);
        }
        catch (Exception e){
            TechnicalException.throwException(ExceptionMessageEnum.TECH_EXP_PROPS_NOT_LOADED);
        }
        return props;
    }

    public static Properties loadProperties(){
        Properties props = loadProperties("queue.properties");
        PropertyValidator.validateProperties(props);
        return props;
    }

}
