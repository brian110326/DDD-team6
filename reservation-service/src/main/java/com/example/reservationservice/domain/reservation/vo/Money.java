package com.example.reservationservice.domain.reservation.vo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Money {

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "currency", length = 3)
	private String currency;

	private Money(BigDecimal amount, String currency) {
		validateAmount(amount);
		validateCurrency(currency);
		this.amount = amount;
		this.currency = currency;
	}

	public static final Money ZERO = new Money(BigDecimal.ZERO, "KRW");

	public static Money of(BigDecimal amount, String currency) {
		return new Money(amount, currency);
	}

	private void validateAmount(BigDecimal amount) {
		if (amount == null) {
			throw new IllegalArgumentException("금액은 필수입니다");
		}
		if (amount.scale() > 2) {
			throw new IllegalArgumentException("금액은 소수점 2자리까지만 가능합니다");
		}
	}

	private void validateCurrency(String currency) {
		if (currency == null || currency.length() != 3) {
			throw new IllegalArgumentException("통화 코드는 3자리여야 합니다 (예: KRW, USD)");
		}
	}
}
