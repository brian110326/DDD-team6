package com.example.reservationservice.domain.reservation;

import java.math.BigDecimal;

import com.example.reservationservice.domain.reservation.vo.Money;

public enum SeatType {
	EMERGENCY_EXIT("비상구 좌석", Money.of(new BigDecimal("15000"), "KRW")),
	FRONT_SEAT("앞좌석", Money.of(new BigDecimal("10000"), "KRW")),
	INFANT_SEAT("유아 동반 좌석", Money.ZERO),
	STANDARD("일반 좌석", Money.ZERO);

	private final String description;
	private final Money additionalFee;

	SeatType(String description, Money additionalFee) {
		this.description = description;
		this.additionalFee = additionalFee;
	}

	public String getDescription() {
		return description;
	}

	public Money getAdditionalFee() {
		return additionalFee;
	}
}
