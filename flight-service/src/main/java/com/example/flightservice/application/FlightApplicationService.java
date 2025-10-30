package com.example.flightservice.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.flightservice.domain.flight.entity.Flight;
import com.example.flightservice.domain.flight.service.FlightDomainService;
import com.example.flightservice.domain.flight.vo.CountryInfo;
import com.example.flightservice.domain.flight.vo.Schedule;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightApplicationService {

	private final FlightDomainService flightDomainService;

	public List<Flight> searchFlights(CountryInfo origin, CountryInfo destination, Schedule schedule) {
		// Domain Service 호출
		return flightDomainService.searchFlights(origin, destination, schedule);
	}

}
