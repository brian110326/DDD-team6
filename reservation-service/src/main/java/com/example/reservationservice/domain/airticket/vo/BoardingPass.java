package com.example.reservationservice.domain.airticket.vo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardingPass {

	@Column(name = "boarding_pass_number", nullable = false, unique = true)
	private String boardingPassNumber;

	@Column(name = "gate", length = 10)
	private String gate;

	@Column(name = "boarding_time", nullable = false)
	private LocalDateTime boardingTime;

	@Column(name = "issued_at")
	private LocalDateTime issuedAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private BoardingPassStatus status;  // ISSUED, USED, CANCELLED

	private BoardingPass(String boardingPassNumber, String seatNumber,
		LocalDateTime boardingTime) {
		validateBoardingPassNumber(boardingPassNumber);
		validateSeatNumber(seatNumber);
		this.boardingPassNumber = boardingPassNumber;
		this.boardingTime = boardingTime;
		this.issuedAt = LocalDateTime.now();
		this.status = BoardingPassStatus.ISSUED;
	}

	public static BoardingPass issue(String boardingPassNumber,
		String seatNumber,
		LocalDateTime boardingTime) {
		return new BoardingPass(boardingPassNumber, seatNumber, boardingTime);
	}

	public void assignGate(String gate) {
		this.gate = gate;
	}

	public void use() {
		if (this.status != BoardingPassStatus.ISSUED) {
			throw new IllegalStateException("이미 사용되었거나 취소된 탑승권입니다.");
		}
		this.status = BoardingPassStatus.USED;
	}

	public void cancel() {
		this.status = BoardingPassStatus.CANCELLED;
	}

	private void validateBoardingPassNumber(String number) {
		if (number == null || number.isBlank()) {
			throw new IllegalArgumentException("탑승권 번호는 필수입니다.");
		}
	}

	private void validateSeatNumber(String seatNumber) {
		if (seatNumber == null || !seatNumber.matches("\\d+[A-Z]")) {
			throw new IllegalArgumentException("올바른 좌석 번호 형식이 아닙니다.");
		}
	}
}