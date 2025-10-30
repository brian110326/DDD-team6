package com.example.paymentservice.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;


@Embeddable
@Getter
@EqualsAndHashCode
public class Money {
    private BigDecimal amount;
    private String currency;

    public Money(BigDecimal amount, String currency) {
        if (amount == null || amount.signum() < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("통화 코드를 지정해야 합니다.");
        }
        this.amount = amount;
        this.currency = currency;
    }

    protected Money() {}

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Money add(Money other) {
        checkCurrency(other);
        return new Money(this.amount.add(other.amount), currency);
    }

    public Money subtract(Money other) {
        checkCurrency(other);
        BigDecimal result = this.amount.subtract(other.amount);
        if (result.signum() < 0) {
            throw new IllegalArgumentException("결과 금액이 0보다 작을 수 없습니다.");
        }
        return new Money(result, currency);
    }

    private void checkCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("통화가 일치하지 않습니다.");
        }
    }

}
