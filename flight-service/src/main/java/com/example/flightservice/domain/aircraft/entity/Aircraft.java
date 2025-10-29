package com.example.flightservice.domain.aircraft.entity;

import java.util.List;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.example.flightservice.domain.aircraft.vo.SeatNumber;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Aircraft는 Aggregate Root로 설계
 * - 항공기 단위의 일관성(좌석 예약 상태 등)을 책임짐
 * - 내부에 Seat 엔티티를 포함하며, 좌석 상태 변경은 Aircraft를 통해서만 수행
 * - Flight Aggregate와 별도로 독립적인 Aggregate로 관리
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Aircraft extends AbstractAggregateRoot<Aircraft> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aircraftId")
	private Long id;

	private String model;
	private int capacity;

	private List<Seat> seats;

	public Aircraft(String model, int capacity) {
		this.model = model;
		this.capacity = capacity;
	}

	public boolean isSeatAvailable(SeatNumber seatNumber) {
		return seats.stream()
			.filter(s -> s.getSeatNumber().equals(seatNumber))
			.noneMatch(Seat::isReserved);
	}

}
