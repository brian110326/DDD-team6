package com.example.reservationservice.domain.reservation.vo;

import java.time.LocalDateTime;

import com.example.reservationservice.domain.airticket.vo.SeatId;
import com.example.reservationservice.domain.reservation.SeatSelectionMethod;
import com.example.reservationservice.domain.reservation.SeatType;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatSelection {

	@Embedded
	@AttributeOverride(name = "id", column = @Column(name = "seat_id"))
	private SeatId seatId;

	@Enumerated(EnumType.STRING)
	@Column(name = "seat_type", length = 20)
	private SeatType seatType;

	@Column(name = "selected_at")
	private LocalDateTime selectedAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "selection_method", length = 20)
	private SeatSelectionMethod selectionMethod;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "amount", column = @Column(name = "seat_fee_amount")),
		@AttributeOverride(name = "currency", column = @Column(name = "seat_fee_currency"))
	})
	private Money additionalFee;

	private SeatSelection(SeatId seatId, SeatType seatType,
		SeatSelectionMethod selectionMethod, Money additionalFee) {
		this.seatId = seatId;
		this.seatType = seatType;
		this.selectionMethod = selectionMethod;
		this.additionalFee = additionalFee;
		this.selectedAt = LocalDateTime.now();
	}

	public static SeatSelection of(SeatId seatId, SeatType seatType,
		SeatSelectionMethod method, Money fee) {
		return new SeatSelection(seatId, seatType, method, fee);
	}
}
