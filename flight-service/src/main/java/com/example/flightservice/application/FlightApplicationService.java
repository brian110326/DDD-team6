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

	public List<Flight> searchFlights() {
		// Domain Service 호출
		// Flight와 FlightOperation을 조회하여 조건에 맞는 리스트 반환
		return flightDomainService.searchFlights();
	}

}
