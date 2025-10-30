package com.example.flightservice.domain.flight.entity;

import com.example.flightservice.domain.flight.vo.CountryInfo;
import com.example.flightservice.domain.flight.vo.Schedule;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
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
 * - FlightOperation은 Flight Aggregate 내에서 운항 관련 책임을 담당
 * - 상태, 일정, 출발/도착 국가 등 운항 단위 핵심 정보를 포함
 * - Flight에서 직접 상태나 일정 변경 대신, FlightOperation 단위에서 관리
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FlightOperation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_operation_id")
	private Long id;

	private String flightCode;
	private String status;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "departureTime", column = @Column(name = "departure_time")),
		@AttributeOverride(name = "arrivalTime", column = @Column(name = "arrival_time"))
	})
	private Schedule schedule;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "code", column = @Column(name = "origin_country_code")),
		@AttributeOverride(name = "name", column = @Column(name = "origin_country_name"))
	})
	private CountryInfo origin;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "code", column = @Column(name = "origin_country_code")),
		@AttributeOverride(name = "name", column = @Column(name = "origin_country_name"))
	})
	private CountryInfo destination;

}
