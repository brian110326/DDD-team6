package com.example.reservationservice.domain.reservation;

public enum SeatSelectionMethod {
	PAID_PRE_SELECT("유료 사전 선택"),
	FREE_AUTO_ASSIGN("무료 자동 배정"),
	COUNTER_ASSIGN("공항 카운터 배정");

	private final String description;

	SeatSelectionMethod(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
