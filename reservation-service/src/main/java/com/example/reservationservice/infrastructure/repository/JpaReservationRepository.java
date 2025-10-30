package com.example.reservationservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reservationservice.domain.reservation.Reservation;

public interface JpaReservationRepository extends JpaRepository<Reservation, Long> {

}
