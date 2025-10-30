package com.example.reservationservice.domain.airticket;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.example.reservationservice.domain.airticket.vo.BoardingPass;
import com.example.reservationservice.domain.airticket.vo.FlightOperationId;
import com.example.reservationservice.domain.airticket.vo.Passenger;
import com.example.reservationservice.domain.airticket.vo.SeatId;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "air_ticket")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AirTicket extends AbstractAggregateRoot<AirTicket> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	@AttributeOverride(name = "id", column = @Column(name = "flight_operation_id"))
	private FlightOperationId flightOperationId;

	@Embedded
	@AttributeOverride(name = "id", column = @Column(name = "seat_id"))
	private SeatId seatId;

	@Embedded
	private Passenger passenger;

	@Embedded
	private BoardingPass boardingPass;
}
