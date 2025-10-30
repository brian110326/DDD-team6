package com.example.paymentservice.domain.vo;

public enum PGProvider {
    KAKAO_PAY("카카오페이"),
    NAVER_PAY("네이버페이"),
    PAYCO("페이코"),
    TOSS("토스"),
    SAMSUNG_PAY("삼성페이"),
    BANK_TRANSFER("계좌이체"),
    CREDIT_CARD("신용/체크카드");

    private final String displayName;

    PGProvider(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
