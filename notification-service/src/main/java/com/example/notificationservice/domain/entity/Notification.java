package com.example.notificationservice.domain.entity;

import com.example.notificationservice.domain.vo.CustomerId;
import com.example.notificationservice.domain.vo.NotificationStatus;
import com.example.notificationservice.domain.vo.NotificationType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "notifications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends AbstractAggregateRoot<Notification> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "customer_id"))
    private CustomerId customerId;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "recipient_phone")
    private String recipientPhone;

    @Column(name = "recipient_email")
    private String recipientEmail;


    public static Notification createEmail(
            CustomerId customerId,
            String recipientEmail,
            String content
    ){
        validateEmail(recipientEmail);
        validateContent(content);

        Notification notification = new Notification();
        notification.customerId = customerId;
        notification.notificationType = NotificationType.EMAIL;
        notification.notificationStatus = NotificationStatus.PENDING;
        notification.content = content;
        notification.recipientEmail = recipientEmail;

        return notification;

    }
    public static Notification createKakao(
            CustomerId customerId,
            String recipientPhone,
            String content) {

        validatePhone(recipientPhone);
        validateContent(content);
        validateKakaoLength(content);

        Notification notification = new Notification();
        notification.customerId = customerId;
        notification.notificationType = NotificationType.KAKAO;
        notification.notificationStatus = NotificationStatus.PENDING;
        notification.content = content;
        notification.recipientPhone = recipientPhone;

        return notification;
    }

    private static void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("이메일은 필수입니다");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("올바르지 않은 이메일 형식입니다");
        }
    }

    private static void validatePhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("휴대폰 번호는 필수입니다");
        }
        String clean = phone.replaceAll("-", "");
        if (!clean.matches("^01[0-9]{8,9}$")) {
            throw new IllegalArgumentException("올바르지 않은 휴대폰 번호입니다");
        }
    }

    private static void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("알림 내용은 필수입니다");
        }
    }



    private static void validateKakaoLength(String content) {
        if (content.length() > NotificationType.KAKAO.getMaxLength()) {
            throw new IllegalArgumentException(
                    "카카오톡 메시지는 " + NotificationType.KAKAO.getMaxLength() +
                            "자를 초과할 수 없습니다");
        }
    }


    public void send() {
        if (!notificationStatus.canSend()) {
            throw new IllegalStateException(
                    "발송할 수 없는 상태입니다: " + notificationStatus.getStatus());
        }

        if (!notificationStatus.canTransitionTo(NotificationStatus.SENDING)) {
            throw new IllegalStateException(
                    notificationStatus.getStatus() + "에서 발송중으로 전이할 수 없습니다");
        }

        this.notificationStatus = NotificationStatus.SENDING;
    }


    public void markAsSent() {
        if (this.notificationStatus != NotificationStatus.SENDING) {
            throw new IllegalStateException(
                    "발송중 상태에서만 완료 처리 가능합니다. 현재 상태: " +
                            notificationStatus.getStatus());
        }

        if (!notificationStatus.canTransitionTo(NotificationStatus.SENT)) {
            throw new IllegalStateException(
                    notificationStatus.getStatus() + "에서 발송완료로 전이할 수 없습니다");
        }

        this.notificationStatus = NotificationStatus.SENT;
    }
    public void markAsFailed(String errorMessage) {
        if (this.notificationStatus != NotificationStatus.SENDING) {
            throw new IllegalStateException(
                    "발송중 상태에서만 실패 처리 가능합니다. 현재 상태: " +
                            notificationStatus.getStatus());
        }

        if (!notificationStatus.canTransitionTo(NotificationStatus.FAILED)) {
            throw new IllegalStateException(
                    notificationStatus.getStatus() + "에서 발송실패로 전이할 수 없습니다");
        }

        if (errorMessage == null || errorMessage.isBlank()) {
            throw new IllegalArgumentException("에러 메시지는 필수입니다");
        }

        this.notificationStatus = NotificationStatus.FAILED;
    }


}
