package com.example.reservationservice.domain.reservation;

public enum FareClass {
	PROMOTIONAL("프로모션 운임", 0.5),      // 50% 적립
	DISCOUNT("할인 운임", 0.75),           // 75% 적립 (수수료 부과 시 50%)
	NORMAL("일반 운임", 1.0),              // 100% 적립 (수수료 부과 시 10%)
	FLEX("플렉스 운임", 1.5);              // 150% 적립

	private final String description;
	private final double mileageRate;

	FareClass(String description, double mileageRate) {
		this.description = description;
		this.mileageRate = mileageRate;
	}

	public String getDescription() {
		return description;
	}

	public double getMileageRate() {
		return mileageRate;
	}

	//수수료 부가시
	public double getMileageRateWithFee() {
		return switch (this) {
			case DISCOUNT -> 0.5;   // 75% → 50%
			case NORMAL -> 0.1;     // 100% → 10%
			default -> mileageRate;
		};
	}
}
