package com.ecommerce.application.handler;

import lombok.Getter;

@Getter
public enum BusinessErrorCodes {

    USER_NOT_FOUND("USR_001", "User not found"),
    CART_NOT_FOUND("CRT_001", "Cart not found"),
    CART_ITEM_NOT_FOUND("CRT_002", "Cart item not found"),
    PAYMENT_NOT_FOUND("PAY_001", "Payment not found"),
    SHIPPING_NOT_FOUND("SHP_001", "Shipping not found"),
    REVIEW_NOT_FOUND("REV_001","Review not found"),
    INVALID_REQUEST("REQ_001", "Invalid request");

    private final String code;
    private final String message;

    BusinessErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
