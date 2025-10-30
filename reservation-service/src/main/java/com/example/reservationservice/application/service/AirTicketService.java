package com.example.reservationservice.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservationservice.domain.repository.AirTicketRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirTicketService {
	private final AirTicketRepository airTicketRepository;

	@Transactional
	public Long issueTicket() {
		// 예약 정보 조회 및 상태 확인 (CONFIRMED 상태여야 발행 가능)

		// 승객 정보 입력 받아서 항공권 발행

		// 항공권 발행 이벤트 발행 (탑승권 생성 트리거)

		return null;
	}
}
