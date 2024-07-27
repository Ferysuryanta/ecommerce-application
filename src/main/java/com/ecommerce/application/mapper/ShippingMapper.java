package com.ecommerce.application.mapper;

import com.ecommerce.application.dto.ShippingDto;
import com.ecommerce.application.model.Shipping;
import org.springframework.stereotype.Component;

@Component
public class ShippingMapper {
    public ShippingDto toShippingDto(Shipping shipping) {
        if (shipping == null) {
            return null;
        }
        ShippingDto shippingDto = new ShippingDto();
        shippingDto.setShippingId(shipping.getShippingId());
        shippingDto.setOrder(new OrderMapper().toOrderDto(shipping.getOrder()));
        shippingDto.setAddress(shipping.getAddress());
        shippingDto.setShippingDate(shipping.getShippingDate());
        shippingDto.setShippingMethod(shipping.getShippingMethod());
        shippingDto.setTrackingNumber(shipping.getTrackingNumber());
        return shippingDto;
    }

    public Shipping toShipping(ShippingDto shippingDto) {
        if (shippingDto == null) {
            return null;
        }
        Shipping shipping = new Shipping();
        shipping.setShippingId(shippingDto.getShippingId());
        shipping.setOrder(new OrderMapper().toOrder(shippingDto.getOrder()));
        shipping.setAddress(shippingDto.getAddress());
        shipping.setShippingDate(shippingDto.getShippingDate());
        shipping.setShippingMethod(shippingDto.getShippingMethod());
        shipping.setTrackingNumber(shippingDto.getTrackingNumber());
        return shipping;
    }
}
