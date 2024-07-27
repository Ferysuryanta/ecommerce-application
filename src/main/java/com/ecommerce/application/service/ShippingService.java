package com.ecommerce.application.service;

import com.ecommerce.application.dto.ShippingDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShippingService {

    ShippingDto createShipping(ShippingDto shippingDto);
    Optional<ShippingDto> getShippingById(UUID shippingId);
    List<ShippingDto> getAllShippings();
    ShippingDto updateShipping(UUID shippingId, ShippingDto shippingDto);
    void deleteShipping(UUID shippingId);
}
