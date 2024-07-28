package com.ecommerce.application.service.impl;

import com.ecommerce.application.dto.ShippingDto;
import com.ecommerce.application.exception.ResourceNotFoundException;
import com.ecommerce.application.handler.BusinessErrorCodes;
import com.ecommerce.application.mapper.ShippingMapper;
import com.ecommerce.application.repository.ShippingRepository;
import com.ecommerce.application.service.ShippingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;
    private final ShippingMapper shippingMapper;

    public ShippingServiceImpl(ShippingRepository shippingRepository, ShippingMapper shippingMapper) {
        this.shippingRepository = shippingRepository;
        this.shippingMapper = shippingMapper;
    }

    @Override
    @Transactional
    public ShippingDto createShipping(ShippingDto shippingDto) {
        var shipping = shippingMapper.toShipping(shippingDto);
        var savedShipping = shippingRepository.save(shipping);
        return shippingMapper.toShippingDto(savedShipping);
    }

    @Override
    public Optional<ShippingDto> getShippingById(UUID shippingId) {
        return shippingRepository.findById(shippingId)
                .map(shippingMapper::toShippingDto);
    }

    @Override
    public List<ShippingDto> getAllShippings() {
        return shippingRepository.findAll()
                .stream()
                .map(shippingMapper::toShippingDto)
                .toList();
    }

    @Override
    @Transactional
    public ShippingDto updateShipping(UUID shippingId, ShippingDto shippingDto) {
        if (shippingRepository.existsById(shippingId)) {
            var shipping = shippingMapper.toShipping(shippingDto);
            shipping.setShippingId(shippingId);
            var updateShipping = shippingRepository.save(shipping);
            return shippingMapper.toShippingDto(updateShipping);
        } else {
            throw new ResourceNotFoundException(BusinessErrorCodes.SHIPPING_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deleteShipping(UUID shippingId) {
        if (shippingRepository.existsById(shippingId)) {
            shippingRepository.deleteById(shippingId);
        } else {
            throw new ResourceNotFoundException(BusinessErrorCodes.SHIPPING_NOT_FOUND);
        }
    }
}
