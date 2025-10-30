package com.example.reservationservice.domain.airticket.vo;

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
public class FlightOperationId {

	@Column(name = "flight_operation_id")
	private Long id;

	private FlightOperationId(Long id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("유효하지 않은 항공편 운항 ID입니다");
		}
		this.id = id;
	}

	public static FlightOperationId of(Long id) {
		return new FlightOperationId(id);
	}
}