package com.example.paymentservice.domain.vo;

public enum PaymentStatus {
    REQUESTED("PG 결제 요청 중") {
        @Override
        public boolean canTransitionTo(PaymentStatus newStatus) {
            return newStatus == SUCCESS || newStatus == FAILED || newStatus == CANCEL_REQUESTED;
        }
    },

    SUCCESS("결제 성공") {
        @Override
        public boolean canTransitionTo(PaymentStatus newStatus) {
            return newStatus == REFUND_REQUESTED || newStatus == CANCEL_REQUESTED;
        }
    },

    FAILED("결제 실패") {
        @Override
        public boolean canTransitionTo(PaymentStatus newStatus) {
            return newStatus == REQUESTED;
        }
    },

    CANCEL_REQUESTED("결제 취소 요청 중") {
        @Override
        public boolean canTransitionTo(PaymentStatus newStatus) {
            return newStatus == CANCELLED || newStatus == CANCEL_FAILED;
        }
    },

    CANCELLED("결제 취소 완료") {
        @Override
        public boolean canTransitionTo(PaymentStatus newStatus) {
            return false;
        }
    },

    CANCEL_FAILED("결제 취소 실패") {
        @Override
        public boolean canTransitionTo(PaymentStatus newStatus) {
            return newStatus == CANCEL_REQUESTED; // 재요청 가능
        }
    },

    REFUND_REQUESTED("환불 요청 중") {
        @Override
        public boolean canTransitionTo(PaymentStatus newStatus) {
            return newStatus == REFUNDED || newStatus == REFUND_FAILED;
        }
    },

    REFUNDED("환불 완료") {
        @Override
        public boolean canTransitionTo(PaymentStatus newStatus) {
            return false;
        }
    },

    REFUND_FAILED("환불 실패") {
        @Override
        public boolean canTransitionTo(PaymentStatus newStatus) {
            return newStatus == REFUND_REQUESTED; // 재시도 가능
        }
    };

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean canTransitionTo(PaymentStatus newStatus);

    public boolean canBeCancelled() {
        return this == REQUESTED || this == SUCCESS;
    }

    public boolean canBeRefunded() {
        return this == SUCCESS;
    }

    public boolean canBePaid() {
        return this == REQUESTED;
    }
}
