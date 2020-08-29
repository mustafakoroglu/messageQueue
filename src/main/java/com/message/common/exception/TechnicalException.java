package com.message.common.exception;

import com.message.common.enums.ExceptionMessageEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class TechnicalException extends RuntimeException {

    public TechnicalException(ExceptionMessageEnum ex){
        super(ex.getMessage());
    }

    public static TechnicalException throwException(ExceptionMessageEnum ex,String... params) throws TechnicalException {
        TechnicalException technicalException = new TechnicalException(ex);
        System.out.println(new Date() + ex.toString() + (params != null ? " :"+StringUtils.join(params,",") : ""));
        technicalException.printStackTrace();
        throw technicalException;
    }
}
