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
public class SeatId {

	@Column(name = "seat_id")
	private Long id;

	private SeatId(Long id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("유효하지 않은 좌석 ID입니다");
		}
		this.id = id;
	}

	public static SeatId of(Long id) {
		return new SeatId(id);
	}
}