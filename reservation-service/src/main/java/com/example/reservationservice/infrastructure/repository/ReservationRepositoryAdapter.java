package com.example.reservationservice.infrastructure.repository;

import org.springframework.stereotype.Component;

import com.example.reservationservice.domain.repository.ReservationRepository;
import com.example.reservationservice.domain.reservation.Reservation;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationRepositoryAdapter implements ReservationRepository {
	private final JpaReservationRepository jpaReservationRepository;

	@Override
	public Reservation save(Reservation reservation) {
		return jpaReservationRepository.save(reservation);
	}
}
