package com.ecommerce.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {

    private UUID paymentId;
    private OrderDto order;
    private LocalDate paymentDate;
    private String paymentMethod;
    private BigDecimal amount;
}
