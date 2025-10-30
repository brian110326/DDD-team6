package com.example.reservationservice.infrastructure.repository;

import org.springframework.stereotype.Component;

import com.example.reservationservice.domain.airticket.AirTicket;
import com.example.reservationservice.domain.repository.AirTicketRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AirTicketRepositoryAdapter implements AirTicketRepository {
	private final JpaAirTicketRepository jpaAirTicketRepository;

	@Override
	public AirTicket save(AirTicket order) {
		return jpaAirTicketRepository.save(order);
	}
}
