package com.nvz.secubank.service;

import com.nvz.secubank.dto.NotificationDto;
import com.nvz.secubank.entity.Notification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NotificationService {
    void saveNotification(String userEmail, String message);
    void deleteNotification(Long notificationId);
    List<Notification> getNotificationsByUserEmail(String userEmail);
    void markNotificationAsRead(Long notificationId);
}
