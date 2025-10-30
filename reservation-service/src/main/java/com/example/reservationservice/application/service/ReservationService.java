package com.example.reservationservice.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservationservice.domain.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {
	private final ReservationRepository reservationRepository;

	@Transactional
	public Long createReservation() {
		//예약 정보 등록
		return null;
	}

	@Transactional
	public Long registerSeat() {
		// 예약 정보 존재 확인(상태도 확인)

		// 요청으로 받은 좌석 id로 좌석 등록 이벤트 발행(차감 이벤트)

		// 좌석 정보 조회 api를 통해 좌석 정보 확인후 예약쪽 정보에 update
		return null;
	}
}
