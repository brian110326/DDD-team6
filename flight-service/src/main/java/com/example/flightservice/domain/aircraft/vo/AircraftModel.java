package com.example.flightservice.domain.aircraft.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AircraftModel {

	private String name;

	public AircraftModel(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("항공기 모델명은 비어 있을 수 없습니다.");
		}

		this.name = name;
	}

}
