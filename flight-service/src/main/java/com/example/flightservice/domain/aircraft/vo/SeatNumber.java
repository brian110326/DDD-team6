package com.example.flightservice.domain.aircraft.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * - SeatNumber는 Seat 엔티티에서 좌석 번호 의미를 표현하는 Value Object
 * - 식별자가 아닌 값 자체의 의미로 도메인에서 사용
 * - 불변성과 동등성(Equals & HashCode)을 보장하여 안전하게 재사용 가능
 */

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatNumber {

	private String seat;

	public SeatNumber(String seat) {
		if (seat == null || seat.isEmpty()) {
			throw new IllegalArgumentException("좌석 정보 번호가 필요합니다.");
		}
	}

}
