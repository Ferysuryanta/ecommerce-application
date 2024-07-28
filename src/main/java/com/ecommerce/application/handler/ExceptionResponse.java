package com.ecommerce.application.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {

    private int statusCode;
    private String message;
    private String errorCode;

    public ExceptionResponse(int statusCode, String message, String errorCode) {
        this.statusCode = statusCode;
        this.message = message;
        this.errorCode = errorCode;
    }

}
