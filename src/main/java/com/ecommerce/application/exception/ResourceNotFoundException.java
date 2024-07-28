package com.ecommerce.application.exception;

import com.ecommerce.application.handler.BusinessErrorCodes;

public class ResourceNotFoundException extends RuntimeException {

    private final String code;
    public ResourceNotFoundException(BusinessErrorCodes errorCodes) {
        super(errorCodes.getMessage());
        this.code = errorCodes.getCode();
    }

    public String getCode() {
        return code;
    }
}
