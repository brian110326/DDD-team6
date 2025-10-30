package com.example.paymentservice.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class PGTransactionId {

    private String id;

    private PGTransactionId(String id) {
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("유효하지 않은 결제 삭별 번호 입니다");
        }
        this.id = id;
    }

    public static PGTransactionId of(String id) {
        return new PGTransactionId(id);
    }
}
