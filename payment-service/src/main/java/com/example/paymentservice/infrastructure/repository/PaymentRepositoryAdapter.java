package com.example.paymentservice.infrastructure.repository;

import com.example.paymentservice.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;
}
