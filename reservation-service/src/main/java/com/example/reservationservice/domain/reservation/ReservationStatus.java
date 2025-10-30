package com.example.reservationservice.domain.reservation;

public enum ReservationStatus {
	PENDING,      // 결제 대기 중
	CONFIRMED,    // 예약 확정 (결제 완료)
	CANCELLED     // 취소됨
}

