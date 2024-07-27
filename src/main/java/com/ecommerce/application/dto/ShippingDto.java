package com.ecommerce.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShippingDto {

    private UUID shippingId;
    private OrderDto order;
    private String address;
    private LocalDate shippingDate;
    private String shippingMethod;
    private String trackingNumber;
}
