package com.example.paymentservice.domain.vo;


import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class PassengerId {
    private Long id;

    private PassengerId(Long id) {
        if(id == null || id <= 0){
            throw new IllegalArgumentException("유효하지 않은 승객 ID 입니다");
        }
        this.id = id;
    }

    public static PassengerId of(Long id) {
        return new PassengerId(id);
    }
}
