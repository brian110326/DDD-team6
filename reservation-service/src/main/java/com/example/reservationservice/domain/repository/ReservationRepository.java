package com.example.reservationservice.domain.repository;

import com.example.reservationservice.domain.reservation.Reservation;

public interface ReservationRepository {
	Reservation save(Reservation reservation);
}
