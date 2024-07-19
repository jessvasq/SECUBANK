package com.nvz.secubank.service;

import com.nvz.secubank.dto.NotificationDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NotificationService {
    void saveNotification(NotificationDto notificationDto);
    void deleteNotification(Long notificationId);
    List<NotificationDto> getNotificationsByUserId(Long userId);
    NotificationDto getNotificationById(Long notificationId);
    NotificationDto updateNotification(Long notificationId, NotificationDto notificationDto);
}
