package com.example.paymentservice.domain.entity;

import com.example.paymentservice.domain.vo.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "passenger_id"))
    private PassengerId passengerId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "reservation_id"))
    private ReservationId reservationId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PGProvider pgProvider;

    @Embedded
    private PGTransactionId pgTransactionId;

    @Embedded
    private Money amount;

    private LocalDateTime approvedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String failureReason;

    //결제 승인
    public void approve(String pgTransactionId) {
        this.pgTransactionId = PGTransactionId.of(pgTransactionId);
        this.paymentStatus = PaymentStatus.SUCCESS;
        this.approvedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    //실패
    public void fail(String reason) {
        this.paymentStatus = PaymentStatus.FAILED;
        this.failureReason = reason;
        this.updatedAt = LocalDateTime.now();
    }

    //취소
    public void cancel() {
        this.paymentStatus = PaymentStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }
}
