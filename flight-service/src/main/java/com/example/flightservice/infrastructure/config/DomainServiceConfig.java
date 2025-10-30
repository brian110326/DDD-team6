package com.example.flightservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.flightservice.domain.aircraft.service.AircraftDomainService;
import com.example.flightservice.domain.flight.service.FlightDomainService;

@Configuration
public class DomainServiceConfig {

	@Bean
	public FlightDomainService flightDomainService() {
		return new FlightDomainService();
	}

	@Bean
	public AircraftDomainService aircraftDomainService() {
		return new AircraftDomainService();
	}

}
