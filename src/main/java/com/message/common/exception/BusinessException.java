package com.message.common.exception;

import com.message.common.enums.ExceptionMessageEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class BusinessException extends Exception{

    private ExceptionMessageEnum exceptionMessage;

    public BusinessException(ExceptionMessageEnum ex){
        super(ex.getMessage());
        this.exceptionMessage = ex;
    }

    public static BusinessException throwException(ExceptionMessageEnum ex,String... params) throws BusinessException{
        BusinessException businessException = new BusinessException(ex);
        System.out.println(new Date() + ex.toString() + (params != null ? " :"+StringUtils.join(params,",") : ""));
        businessException.printStackTrace();
        throw businessException;
    }

    public Integer getCode(){
        return exceptionMessage == null ? null : exceptionMessage.getCode();
    }
}
