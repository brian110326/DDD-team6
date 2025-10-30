package com.example.flightservice.domain.aircraft.entity;

import com.example.flightservice.domain.aircraft.vo.SeatNumber;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * - Seat는 항공편 단위에서 좌석 정보를 관리하는 엔티티
 * - VO인 SeatNumber를 포함하여 좌석 번호 의미를 명확히 표현
 * - 예약 로직은 포함하지 않고, 단순 데이터와 상태 표현만 담당
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	@AttributeOverride(name = "seatNumber", column = @Column(name = "seat_number"))
	private SeatNumber seatNumber;

	private boolean isReserved;

	public Seat(SeatNumber seatNumber) {
		this.seatNumber = seatNumber;
		this.isReserved = false;
	}

}
