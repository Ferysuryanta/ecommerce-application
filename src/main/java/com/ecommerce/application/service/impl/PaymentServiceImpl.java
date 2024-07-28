package com.ecommerce.application.service.impl;

import com.ecommerce.application.dto.PaymentDto;
import com.ecommerce.application.exception.ResourceNotFoundException;
import com.ecommerce.application.handler.BusinessErrorCodes;
import com.ecommerce.application.mapper.PaymentMapper;
import com.ecommerce.application.repository.PaymentRepository;
import com.ecommerce.application.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ecommerce.application.handler.BusinessErrorCodes.PAYMENT_NOT_FOUND;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    @Transactional
    public PaymentDto createPayment(PaymentDto paymentDto) {
        var payment = paymentMapper.toPayment(paymentDto);
        var savedPayment = paymentRepository.save(payment);
        return paymentMapper.toPaymentDto(savedPayment);
    }

    @Override
    public Optional<PaymentDto> getPaymentById(UUID paymentId) {
        return paymentRepository.findById(paymentId)
                .map(paymentMapper::toPaymentDto);
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toPaymentDto)
                .toList();
    }

    @Override
    @Transactional
    public PaymentDto updatePayment(UUID paymentId, PaymentDto paymentDto) {
        if (paymentRepository.existsById(paymentId)) {
            var payment = paymentMapper.toPayment(paymentDto);
            var savedUpdate = paymentRepository.save(payment);
            return paymentMapper.toPaymentDto(savedUpdate);
        } else {
            throw new ResourceNotFoundException(PAYMENT_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deletePayment(UUID paymentId) {
        if (paymentRepository.existsById(paymentId)) {
            paymentRepository.deleteById(paymentId);
        } else {
            throw new ResourceNotFoundException(PAYMENT_NOT_FOUND);
        }
    }
}
