package com.ecommerce.application.repository;

import com.ecommerce.application.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShippingRepository extends JpaRepository<Shipping, UUID> {
}
