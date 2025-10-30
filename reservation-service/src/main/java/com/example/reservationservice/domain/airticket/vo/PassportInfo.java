package com.example.reservationservice.domain.airticket.vo;

import java.time.LocalDate;

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
public class PassportInfo {
	@Column(name = "passport_number")
	private String passportNumber;

	@Column(name = "passport_issuing_country")
	private String issuingCountry;

	@Column(name = "passport_expiry_date")
	private LocalDate expiryDate;

	@Column(name = "nationality")
	private String nationality;

	private PassportInfo(String passportNumber, String issuingCountry,
		LocalDate expiryDate, String nationality) {
		validatePassportNumber(passportNumber);
		validateExpiryDate(expiryDate);
		this.passportNumber = passportNumber;
		this.issuingCountry = issuingCountry;
		this.expiryDate = expiryDate;
		this.nationality = nationality;
	}

	public static PassportInfo of(String passportNumber, String issuingCountry,
		LocalDate expiryDate, String nationality) {
		return new PassportInfo(passportNumber, issuingCountry, expiryDate, nationality);
	}

	private void validateExpiryDate(LocalDate expiryDate) {
		LocalDate minimumValidDate = LocalDate.now().plusMonths(3);
		if (expiryDate.isBefore(minimumValidDate)) {
			throw new IllegalArgumentException("여권 유효기간이 3개월 이상 남아있어야 합니다");
		}
	}

	private void validatePassportNumber(String passportNumber) {
		if (passportNumber == null || passportNumber.isBlank()) {
			throw new IllegalArgumentException("여권번호는 필수입니다");
		}
	}

	public boolean isExpired() {
		return expiryDate.isBefore(LocalDate.now());
	}
}