package com.nvz.secubank.entity;

import com.nvz.secubank.entity.enumClasses.NotificationStatus;
import com.nvz.secubank.entity.enumClasses.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Card entity stores notifications sent to users
 * ManyToOne relationship with User
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationID;

    @Column(nullable = false)
    private String message;

    private LocalDateTime timestamp;

    private boolean isRead;

//    @Enumerated(EnumType.STRING)
//    private NotificationStatus status;

//    @Enumerated(EnumType.STRING)
//    private NotificationType notificationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
