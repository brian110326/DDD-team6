package com.example.paymentservice.application;

import com.example.paymentservice.application.command.CanclePaymentCommand;
import com.example.paymentservice.application.command.RequestPaymentCommand;
import com.example.paymentservice.application.command.VerifyPaymentCommand;
import com.example.paymentservice.domain.repository.PaymentRepository;
import com.example.paymentservice.domain.service.PaymentDomainService;
import com.example.paymentservice.infrastructure.external.PassengerClient;
import com.example.paymentservice.infrastructure.external.ReservationClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentDomainService paymentDomainService;

    private final PassengerClient passengerClient;
    private final ReservationClient reservationClient;

    //초기 결제 정보 생성
    public void requestPayment(RequestPaymentCommand command) {
        //1. 결제 요청 커맨드를 통한 결제 엔티티 생성

        //2. 결제 요청 정보 저장
    }

    //결제 승인 처리
    @Transactional
    public void approvePayment(VerifyPaymentCommand command) {
        //1. 예약 번호로 결제 정보 조회

        //2. command에 담긴 PG사 결제 식별 코드로 결제 내역 조회

        /*3. PG사 결제 내역에 맞게 승인 및 실패 처리
        성공시 결제 도메인 서비스에서 승인 처리
        실패시 결제 도메인 서비스에서 실패 처리
         */

        //4. 상태 변경 후 다시 저장

        //5. (서비스 구조에 따라) 결제 성공/실패 이벤트 발행
    }


    //결제 취소 처리
    @Transactional
    public void cancelPayment(CanclePaymentCommand command) {
        //1. 예약 번호로 결제 정보 조회

        //2. 결제 정보에 등록된 식별 코드로 실제 PG사 결제 기록 조회

        //3. 결제 도메인 서비스에서 취소 가능한지 판별 후 취소처리

        //4. 상태 변경 후 다시 저장

        //5. (서비스 구조에 따라) 결제 취소 이벤트 발행
    }

}
