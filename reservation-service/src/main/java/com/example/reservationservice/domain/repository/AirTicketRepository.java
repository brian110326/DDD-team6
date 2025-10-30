package com.example.reservationservice.domain.repository;

import com.example.reservationservice.domain.airticket.AirTicket;

public interface AirTicketRepository {
	AirTicket save(AirTicket airTicket);
}
