package com.example.reservationservice.domain.reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.reservationservice.domain.reservation.vo.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.example.reservationservice.domain.airticket.vo.SeatId;
import com.example.reservationservice.domain.airticket.vo.FlightOperationId;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends AbstractAggregateRoot<Reservation> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private ReservationNumber reservationNumber;

	@Embedded
	@AttributeOverride(name = "id", column = @Column(name = "flight_operation_id"))
	private FlightOperationId flightOperationId;

	@Column(name = "customer_name", nullable = false)
	private String customerName;

	@Embedded
	private ContactInfo contactInfo;

	@Enumerated(EnumType.STRING)
	@Column(name = "cabin_class", nullable = false, length = 20)
	private CabinClass cabinClass;

	@Enumerated(EnumType.STRING)
	@Column(name = "fare_class", nullable = false, length = 20)
	private FareClass fareClass;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private ReservationStatus status;

	@Column(name = "reserved_at", nullable = false)
	private LocalDateTime reservedAt;

	@Column(name = "payment_deadline", nullable = false)
	private LocalDateTime paymentDeadline;

	@Column(name = "confirmed_at")
	private LocalDateTime confirmedAt;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "amount", column = @Column(name = "total_amount")),
		@AttributeOverride(name = "currency", column = @Column(name = "currency"))
	})
	private Money totalAmount;

	// OneToMany로 변경
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "reservation_id")
	private List<SpecialServiceRequest> specialServices = new ArrayList<>();

	@Embedded
	private SeatSelection seatSelection;

	private Reservation(String customerName, ContactInfo contactInfo,
		FlightOperationId flightOperationId,
		CabinClass cabinClass, FareClass fareClass,
		Money totalAmount) {
		this.reservationNumber = ReservationNumber.generate();
		this.customerName = customerName;
		this.contactInfo = contactInfo;
		this.flightOperationId = flightOperationId;
		this.cabinClass = cabinClass;
		this.fareClass = fareClass;
		this.totalAmount = totalAmount;
		this.status = ReservationStatus.PENDING;
		this.reservedAt = LocalDateTime.now();
		this.paymentDeadline = LocalDateTime.now().plusHours(24);
	}

	public static Reservation create(String customerName, ContactInfo contactInfo,
		FlightOperationId flightOperationId,
		CabinClass cabinClass, FareClass fareClass,
		Money totalAmount) {
		return new Reservation(customerName, contactInfo, flightOperationId,
			cabinClass, fareClass, totalAmount);
	}

	// 특별 서비스 신청
	public void requestSpecialService(SpecialServiceType serviceType,
		String remarks,
		LocalDateTime departureTime) {
		validateSpecialServiceRequestTime(departureTime);

		SpecialServiceRequest request = SpecialServiceRequest.create(this, serviceType, remarks);
		this.specialServices.add(request);
	}

	public void selectSeat(SeatId seatId, SeatType seatType,
		SeatSelectionMethod method,
		LocalDateTime departureTime) {
		validateSeatSelectionTime(method, departureTime);

		Money additionalFee = seatType.getAdditionalFee();
		this.seatSelection = SeatSelection.of(seatId, seatType, method, additionalFee);

		//좌석 등록 이벤트 발행
	}

	public void confirm() {
		if (this.status != ReservationStatus.PENDING) {
			throw new IllegalStateException("대기 중인 예약만 확정할 수 있습니다");
		}
		if (isPaymentOverdue()) {
			throw new IllegalStateException("결제 기한이 만료되었습니다");
		}
		this.status = ReservationStatus.CONFIRMED;
		this.confirmedAt = LocalDateTime.now();
	}

	public void cancel() {
		if (this.status == ReservationStatus.CANCELLED) {
			throw new IllegalStateException("이미 취소된 예약입니다");
		}
		this.status = ReservationStatus.CANCELLED;

		// 좌석 및 제거되어야 할 이벤트 발행
	}

	public boolean isPaymentOverdue() {
		return LocalDateTime.now().isAfter(paymentDeadline)
			&& this.status == ReservationStatus.PENDING;
	}

	private void validateSpecialServiceRequestTime(LocalDateTime departureTime) {
		LocalDateTime deadline = departureTime.minusHours(48);
		if (LocalDateTime.now().isAfter(deadline)) {
			throw new IllegalStateException("특별 서비스는 출발 48시간 전까지만 신청 가능합니다");
		}
	}

	private void validateSeatSelectionTime(SeatSelectionMethod method,
		LocalDateTime departureTime) {
		LocalDateTime now = LocalDateTime.now();

		switch (method) {
			case PAID_PRE_SELECT:
				if (now.isAfter(departureTime.minusHours(1))) {
					throw new IllegalStateException("유료 사전 좌석 선택은 출발 1시간 전까지 가능합니다");
				}
				break;
			case FREE_AUTO_ASSIGN:
				if (now.isBefore(departureTime.minusHours(48)) ||
					now.isAfter(departureTime.minusHours(1))) {
					throw new IllegalStateException("무료 자동 배정은 출발 48시간 전부터 1시간 전까지 가능합니다");
				}
				break;
		}
	}

	public boolean hasSeatSelection() {
		return this.seatSelection != null;
	}

	public boolean hasSpecialService(SpecialServiceType serviceType) {
		return specialServices.stream()
			.anyMatch(s -> s.getServiceType() == serviceType);
	}
}