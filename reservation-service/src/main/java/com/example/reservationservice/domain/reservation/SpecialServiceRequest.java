package com.example.reservationservice.domain.reservation;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "special_service_request")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpecialServiceRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_id", nullable = false)
	private Reservation reservation;

	@Enumerated(EnumType.STRING)
	@Column(name = "service_type", nullable = false, length = 30)
	private SpecialServiceType serviceType;

	@Column(name = "requested_at", nullable = false)
	private LocalDateTime requestedAt;

	@Column(name = "remarks", length = 500)
	private String remarks;

	private SpecialServiceRequest(Reservation reservation,
		SpecialServiceType serviceType,
		String remarks) {
		this.reservation = reservation;
		this.serviceType = serviceType;
		this.remarks = remarks;
		this.requestedAt = LocalDateTime.now();
	}

	public static SpecialServiceRequest create(Reservation reservation,
		SpecialServiceType serviceType,
		String remarks) {
		return new SpecialServiceRequest(reservation, serviceType, remarks);
	}
}
