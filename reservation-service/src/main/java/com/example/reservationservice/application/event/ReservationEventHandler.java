package com.example.reservationservice.application.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReservationEventHandler {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePaymentComplete() {
        //예약 확정 로직 실행

        // 예약 정보 조회 및 상태 확인 (PENDING 상태여야 확정 가능)

        // 예약 확정 처리 (status를 CONFIRMED로 변경)

        // 예약 확정 이벤트 발행 (항공권 발급, 알림 전송 이벤트)

    }
}
