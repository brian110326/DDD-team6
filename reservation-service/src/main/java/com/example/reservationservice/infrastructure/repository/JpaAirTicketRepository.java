package com.example.reservationservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reservationservice.domain.airticket.AirTicket;

public interface JpaAirTicketRepository extends JpaRepository<AirTicket, Long> {
}
