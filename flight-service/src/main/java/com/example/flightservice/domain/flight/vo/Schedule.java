package com.example.flightservice.domain.flight.vo;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * - Schedule은 항공편 운항 일정을 표현하는 Value Object
 * - 식별자가 없는 값 단위 객체로, 출발/도착 시간의 의미만 관리
 * - 불변성과 동등성(Equals & HashCode)을 보장하여 안전하게 재사용 가능
 */

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;

	public Schedule(LocalDateTime departureTime, LocalDateTime arrivalTime) {
		if (departureTime == null || arrivalTime == null) {
			throw new IllegalArgumentException("출발 혹은 도착 일정이 필요합니다.");
		}
		if (arrivalTime.isBefore(departureTime)) {
			throw new IllegalArgumentException("도착 시간은 출발 시간 이후여야 합니다.");
		}
	}

}
