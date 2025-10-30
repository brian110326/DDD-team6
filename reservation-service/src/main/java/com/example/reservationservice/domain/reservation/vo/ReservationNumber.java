package com.example.reservationservice.domain.reservation.vo;

import java.util.Random;

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
public class ReservationNumber {
	private static final int CODE_LENGTH = 6;

	@Column(name = "reservation_number", unique = true, length = 6)
	private String value;

	private ReservationNumber(String value) {
		validateFormat(value);
		this.value = value;
	}

	public static ReservationNumber generate() {
		String value = generateRandomCode();
		return new ReservationNumber(value);
	}

	public static ReservationNumber of(String value) {
		return new ReservationNumber(value);
	}

	private static String generateRandomCode() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuilder code = new StringBuilder(CODE_LENGTH);

		for (int i = 0; i < CODE_LENGTH; i++) {
			code.append(chars.charAt(random.nextInt(chars.length())));
		}

		return code.toString();
	}

	private void validateFormat(String value) {
		if (value == null || !value.matches("^[A-Z0-9]{6}$")) {
			throw new IllegalArgumentException("예약번호는 6자리 영문+숫자 조합이어야 합니다");
		}
	}
}
