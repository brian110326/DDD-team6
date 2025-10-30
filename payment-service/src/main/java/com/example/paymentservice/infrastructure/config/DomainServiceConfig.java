package com.example.paymentservice.infrastructure.config;

import com.example.paymentservice.domain.service.PaymentDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServiceConfig {
    @Bean
    public PaymentDomainService paymentDomainService() {
        return new PaymentDomainService();
    }
}
