package com.ecommerce.application.exception;

import com.ecommerce.application.handler.BusinessErrorCodes;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final String code;

    public ApiException(BusinessErrorCodes errorCodes) {
        super(errorCodes.getMessage());
        this.code = errorCodes.getCode();
    }
}
