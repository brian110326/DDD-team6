package com.example.flightservice.application;

import org.springframework.stereotype.Service;

import com.example.flightservice.domain.aircraft.service.AircraftDomainService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AircraftApplicationService {

	private final AircraftDomainService aircraftDomainService;

}
