package com.example.paymentservice.infrastructure.repository;

import com.example.paymentservice.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
}
