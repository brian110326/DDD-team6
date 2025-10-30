package com.example.reservationservice.domain.airticket.vo;

public enum PassengerGender {
	MALE("M", "남성"),
	FEMALE("F", "여성"),
	UNSPECIFIED("X", "미지정");  // 논바이너리, 미공개 등 포함

	private final String code;
	private final String displayName;

	PassengerGender(String code, String displayName) {
		this.code = code;
		this.displayName = displayName;
	}

	public String getCode() {
		return code;
	}
}