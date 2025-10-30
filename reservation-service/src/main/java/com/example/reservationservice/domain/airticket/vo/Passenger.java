package com.example.reservationservice.domain.airticket.vo;

import java.time.LocalDate;

import com.example.reservationservice.domain.airticket.PassengerGender;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Passenger {
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false, length = 20)
	private PassengerGender gender;

	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;

	@Embedded
	private PassportInfo passportInfo;
}
