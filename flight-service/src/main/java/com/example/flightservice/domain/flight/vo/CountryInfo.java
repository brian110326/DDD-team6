package com.example.flightservice.domain.flight.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * - CountryInfo는 항공편 출발/도착 국가 정보를 표현하는 Value Object
 * - 식별자가 아닌 값 자체의 의미로 도메인에서 사용
 * - 불변성과 동등성(Equals & HashCode)을 보장하여 안전하게 재사용 가능
 */

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CountryInfo {

	private String code;
	private String name;

	public CountryInfo(String code, String name) {
		if (code == null || name == null) {
			throw new IllegalArgumentException("국가 정보가 필요합니다.");
		}
		this.code = code;
		this.name = name;
	}

}
