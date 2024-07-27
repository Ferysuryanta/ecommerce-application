package com.ecommerce.application.service;

import com.ecommerce.application.dto.PaymentDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentService {

    PaymentDto createPayment(PaymentDto paymentDto);
    Optional<PaymentDto> getPaymentById(UUID paymentId);
    List<PaymentDto> getAllPayments();
    PaymentDto updatePayment(UUID paymentId, PaymentDto paymentDto);
    void deletePayment(UUID paymentId);
}
