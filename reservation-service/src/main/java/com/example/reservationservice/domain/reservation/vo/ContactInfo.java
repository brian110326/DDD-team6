package com.example.reservationservice.domain.reservation.vo;

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
public class ContactInfo {
	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	private ContactInfo(String phoneNumber, String email) {
		validatePhoneNumber(phoneNumber);
		validateEmail(email);
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public static ContactInfo of(String phoneNumber, String email) {
		return new ContactInfo(phoneNumber, email);
	}

	private void validatePhoneNumber(String phoneNumber) {
		// 전화번호 형식 검증
		if (!phoneNumber.matches("^\\+?[0-9]{10,15}$")) {
			throw new IllegalArgumentException("유효하지 않은 전화번호입니다");
		}
	}

	private void validateEmail(String email) {
		// 이메일 형식 검증
		if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new IllegalArgumentException("유효하지 않은 이메일입니다");
		}
	}
}