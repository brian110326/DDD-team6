package com.example.flightservice.domain.flight.service;

import java.util.List;

import com.example.flightservice.domain.flight.entity.Flight;
import com.example.flightservice.domain.flight.vo.CountryInfo;
import com.example.flightservice.domain.flight.vo.Schedule;

public class FlightDomainService {

	// 항공편 검색
	public List<Flight> searchFlights(CountryInfo origin, CountryInfo destination, Schedule schedule) {
		// Flight와 FlightOperation을 조회하여 조건에 맞는 리스트 반환
		return null;
	}

}
