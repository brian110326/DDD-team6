package com.example.paymentservice.domain.service;

import com.example.paymentservice.domain.entity.Payment;
import com.example.paymentservice.domain.vo.Money;
import com.example.paymentservice.domain.vo.PaymentStatus;

public class PaymentDomainService {

    public void approvePayment(Payment payment, String pgTransactionId, Money amount) {
        if (!payment.getPaymentStatus().canBePaid()) {
            throw new IllegalStateException("결제 승인 불가 상태: " + payment.getPaymentStatus());
        }
        if(!payment.getAmount().getAmount().equals(amount)){
            throw new IllegalStateException("금액이 일치하지 않습니다.");
        }
        payment.approve(pgTransactionId);
    }

    public void failPayment(Payment payment, String pgFailureCode, String pgFailureMessage) {
        if (payment.getPaymentStatus() == PaymentStatus.SUCCESS) {
            throw new IllegalStateException("이미 결제 성공 상태입니다.");
        }
        String combinedReason = String.format("PG_CODE: %s, MSG: %s", pgFailureCode, pgFailureMessage);
        payment.fail(combinedReason);
    }


    public void cancelPayment(Payment payment) {
        if (!payment.getPaymentStatus().canBeCancelled()) {
            throw new IllegalStateException("결제 취소 불가 상태: " + payment.getPaymentStatus());
        }
        payment.cancel();
    }

    public boolean canTransition(Payment payment, PaymentStatus newStatus) {
        return payment.getPaymentStatus().canTransitionTo(newStatus);
    }
}
