package com.message.common.validator;

import com.message.dto.MessageDTO;
import com.message.common.enums.ActionTypeEnum;
import com.message.common.enums.ExceptionMessageEnum;
import com.message.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class MessageValidator {

    public static void validateMessage(MessageDTO messageDTO) throws BusinessException {
        if(messageDTO == null){
            BusinessException.throwException(ExceptionMessageEnum.MESSAGE_NULL);
        }
        else if(messageDTO.getActionType() == null){
            BusinessException.throwException(ExceptionMessageEnum.ACTION_TYPE_NULL);
        }
        else if(StringUtils.isBlank(messageDTO.getText())){
            BusinessException.throwException(ExceptionMessageEnum.TEXT_BLANK);
        }
        validateCustomerAddress(messageDTO.getCustomerAddress(),messageDTO.getActionType());

    }

    public static void validateCustomerAddress(String customerAddress, ActionTypeEnum actionType) throws BusinessException{
        if(StringUtils.isBlank(customerAddress)){
            BusinessException.throwException(ExceptionMessageEnum.CUSTOMER_ADDRESS_BLANK);
        }
        else if(actionType.equals(ActionTypeEnum.SMS) && !isPhoneNumberValid(customerAddress)){
            BusinessException.throwException(ExceptionMessageEnum.CELL_PHONE_NO_NOT_VALID,customerAddress);
        }
        else if(actionType.equals(ActionTypeEnum.EMAIL) && !isEmailAddressValid(customerAddress)){
            BusinessException.throwException(ExceptionMessageEnum.EMAIL_ADDRESS_NOT_VALID,customerAddress);
        }
        else if(actionType.equals(ActionTypeEnum.PUSH) && !isMobileCellIdValid(customerAddress)){
            BusinessException.throwException(ExceptionMessageEnum.MOBILE_CELL_ID_NOT_VALID,customerAddress);
        }
    }

    /*
     * valid phone number assumed: +123123123, 12312123
     */
    public static boolean isPhoneNumberValid(String phoneNumber){
        if(StringUtils.isNumeric(phoneNumber)){
            return true;
        }
        else if(phoneNumber.charAt(0) == '+' && StringUtils.isNumeric(phoneNumber.substring(1))){
            return true;
        }
        return false;
    }

    public static boolean isEmailAddressValid(String emailAddress){
        return EmailValidator.getInstance().isValid(emailAddress);
    }

    /*
     *it should be mobile cell id(alphanumeric number)) TODO:'alphanumeric number'?
     */
    public static boolean isMobileCellIdValid(String mobileCellId){
        return StringUtils.isAlphanumeric(mobileCellId);
    }

}
