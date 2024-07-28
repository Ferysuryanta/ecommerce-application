package com.ecommerce.application.exception;

import com.ecommerce.application.exception.ApiException;
import com.ecommerce.application.handler.BusinessErrorCodes;

public class InvalidRequestException extends ApiException {
    public InvalidRequestException(BusinessErrorCodes errorCodes) {
        super(errorCodes);
    }
}
