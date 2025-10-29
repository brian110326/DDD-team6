package com.example.flightservice.domain.flight.entity;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.example.flightservice.domain.aircraft.entity.Aircraft;
import com.example.flightservice.domain.flight.vo.Fare;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Flight Aggregate Root 설계 이유:
 * - 항공편 단위를 대표하는 핵심 엔티티로서, 관련 하위 엔티티와 VO의 일관성을 관리하고 통제하는 책임을 가짐
 * - FlightOperation, Aircraft, Seats, Fare 등 여러 객체를 포함하며,
 *   Aggregate 내부 상태 변경 시 외부에서 직접 조작하지 않고 Root를 통해 일관성을 보장
 * - Flight 자체가 도메인에서 "항공편"이라는 핵심 개념을 나타내므로 Root로 적합
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Flight extends AbstractAggregateRoot<Flight> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_operation_id")
	private FlightOperation flightOperation;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "aircraftId", nullable = false)
	private Aircraft aircraft;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "amount", column = @Column(name = "fare_amount")),
		@AttributeOverride(name = "currency", column = @Column(name = "fare_currency"))
	})
	private Fare fare;

	/**
	 * 생성 메소드 (Factory Method)
	 * - Aggregate Root 생성 시 반드시 필수 필드를 전달
	 * - 생성 시점에 필수 값 유효성 검증 수행
	 */
	public static Flight createFlight(FlightOperation flightOperation, Aircraft aircraft,
		Fare fare) {

		validateFlightOperation(flightOperation);
		validateAircraft(aircraft);
		validateFare(fare);

		Flight flight = new Flight();
		flight.flightOperation = flightOperation;
		flight.aircraft = aircraft;
		flight.fare = fare;

		return flight;
	}

	private static void validateFlightOperation(FlightOperation flightOperation) {
		if (flightOperation == null) {
			throw new IllegalArgumentException("운항 정보 필요");
		}
	}

	private static void validateAircraft(Aircraft aircraft) {
		if (aircraft == null) {
			throw new IllegalArgumentException("항공기 정보 필요");
		}
	}

	private static void validateFare(Fare fare) {
		if (fare == null) {
			throw new IllegalArgumentException("운임 정보 필요");
		}
	}

}
