package com.example.paymentservice.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class ReservationId {
    private Long id;

    private ReservationId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("유효하지 않은 예약 ID입니다");
        }
        this.id = id;
    }

    public static ReservationId of(Long id) {
        return new ReservationId(id);
    }
}
