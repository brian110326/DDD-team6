package com.example.reservationservice.domain.reservation;

public enum SpecialServiceType {
	WHEELCHAIR("휠체어", false, 0),
	INFANT_BASSINET("유아 바구니", true, 10),  // 수량 제한
	SPECIAL_MEAL_VEGETARIAN("채식 기내식", false, 0),
	SPECIAL_MEAL_HALAL("할랄 기내식", false, 0),
	SPECIAL_MEAL_DIABETIC("당뇨 기내식", false, 0),
	EXTRA_BAGGAGE("추가 수하물", true, 50);

	private final String description;
	private final boolean hasQuantityLimit;
	private final int maxQuantity;

	SpecialServiceType(String description, boolean hasQuantityLimit, int maxQuantity) {
		this.description = description;
		this.hasQuantityLimit = hasQuantityLimit;
		this.maxQuantity = maxQuantity;
	}

	public boolean hasQuantityLimit() {
		return hasQuantityLimit;
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}

	public String getDescription() {
		return description;
	}
}
