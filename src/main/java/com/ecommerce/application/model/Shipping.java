package com.ecommerce.application.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "shipping")
public class Shipping {

    @Id
    @GeneratedValue
    private UUID shippingId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @NotBlank(message = "Shipping adress is mandatory")
    private String address;

    @NotNull(message = "Shipping date is mandatory")
    private LocalDate shippingDate;

    @NotBlank(message = "Shipping method is mandatory")
    private String shippingMethod;

    @NotBlank(message = "Tracking number is mandatory")
    private String trackingNumber;
}
