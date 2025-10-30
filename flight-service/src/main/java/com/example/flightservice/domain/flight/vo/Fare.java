package com.example.flightservice.domain.flight.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * - Fare는 항공권 가격 정보를 표현하는 Value Object
 * - 식별자가 아닌 값 자체의 의미로 도메인에서 사용
 * - 불변성과 동등성(Equals & HashCode)을 보장하여 안전하게 재사용 가능
 */

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Fare {

	private double amount; //실제 금액
	private String currency; //"UDS", "KRW"

	public Fare(double amount, String currency) {
		if (amount < 0) {
			throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
		}
		if (currency == null || currency.isBlank()) {
			throw new IllegalArgumentException("통화 단위는 필수입니다.");
		}
		this.amount = amount;
		this.currency = currency;
	}

}
