package com.ecommerce.application.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue
    private UUID paymentId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull(message = "Payment date is mandatory")
    private LocalDate paymentDate;

    @NotBlank(message = "Payment method is mandatory")
    private String paymentMethod;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;
}
