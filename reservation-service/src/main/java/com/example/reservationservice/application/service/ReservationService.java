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

	@Transactional
	public void confirmReservation() {
		// 예약 정보 조회 및 상태 확인 (PENDING 상태여야 확정 가능)

		// 결제 완료 여부 확인 (Payment API 조회)

		// 예약 확정 처리 (status를 CONFIRMED로 변경)

		// 예약 확정 이벤트 발행 (항공권 발급, 알림 전송 이벤트)
	}
}
