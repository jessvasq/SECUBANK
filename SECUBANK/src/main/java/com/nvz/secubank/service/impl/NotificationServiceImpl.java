package com.nvz.secubank.service.impl;

import com.nvz.secubank.entity.Notification;
import com.nvz.secubank.entity.User;
import com.nvz.secubank.repository.NotificationRepository;
import com.nvz.secubank.repository.UserRepository;
import com.nvz.secubank.service.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveNotification(String userEmail, String message) {
        //find the logged-in user by its email
        User user = userRepository.findByEmail(userEmail);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notification.setRead(false);
        notification.setUser(user);

        notificationRepository.save(notification);

    }

    @Override
    public void deleteNotification(Long notificationId) {
       notificationRepository.deleteById(notificationId);
    }

    @Override
    public List<Notification> getNotificationsByUserEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return notificationRepository.findByUser(user);
    }

    @Override
    public void markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).get();
        notification.setRead(true);
        notificationRepository.save(notification);
    }


}
