package com.example.notificationservice.domain.vo;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

//고객 Id 객체
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerId {

    private Long id;
    private CustomerId(Long id){
        if(id == null || id<=0){
            throw new IllegalArgumentException("유효하지 않은 고객 ID 입니다.");

        }
        this.id = id;
    }

    public static CustomerId of(Long id){
        return new CustomerId(id);
    }
}
