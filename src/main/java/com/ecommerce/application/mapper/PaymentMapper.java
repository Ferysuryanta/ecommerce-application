package com.ecommerce.application.mapper;

import com.ecommerce.application.dto.PaymentDto;
import com.ecommerce.application.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentDto toPaymentDto(Payment payment) {
        if (payment == null) {
            return null;
        }
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(payment.getPaymentId());
        paymentDto.setOrder(new OrderMapper().toOrderDto(payment.getOrder()));
        paymentDto.setPaymentDate(payment.getPaymentDate());
        paymentDto.setPaymentMethod(payment.getPaymentMethod());
        paymentDto.setAmount(payment.getAmount());
        return paymentDto;
    }

    public Payment toPayment(PaymentDto paymentDto) {
        if (paymentDto == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setPaymentId(paymentDto.getPaymentId());
        payment.setOrder(new OrderMapper().toOrder(paymentDto.getOrder()));
        payment.setPaymentDate(paymentDto.getPaymentDate());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setAmount(paymentDto.getAmount());
        return payment;
    }
}
