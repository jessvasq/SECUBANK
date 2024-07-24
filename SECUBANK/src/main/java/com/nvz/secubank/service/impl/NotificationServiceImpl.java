package com.nvz.secubank.service.impl;

import com.nvz.secubank.entity.Account;
import com.nvz.secubank.entity.Notification;
import com.nvz.secubank.entity.User;
import com.nvz.secubank.repository.NotificationRepository;
import com.nvz.secubank.repository.UserRepository;
import com.nvz.secubank.service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Override NotificationService methods and provide business logic
 */
@Slf4j
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    static final BigDecimal setLimit = new BigDecimal(100);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * @param userEmail Fetch current logged-in user's email
     * @param message takes input data to create and persist a Notification
     */
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

    /**
     * @param notificationId Fetch a notification by its id and deletes it from the db
     */
    @Override
    public void deleteNotification(Long notificationId) {
       notificationRepository.deleteById(notificationId);
    }

    /**
     * @param userEmail Fetch a user by its email
     * @return a list of Notifications associated to the current logged-in user
     */
    @Override
    public List<Notification> getNotificationsByUserEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return notificationRepository.findByUser(user);
    }

    /**
     * @param notificationId Fetch a notification by its id
     * mark 'read' property to true and persist to DB
     */
    @Override
    public void markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).get();
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    /**
     *
     * @param userEmail Fetch current logged-in user's email
     * Iterate through the user accounts, check its balance and generate a notification if the balance is low
     */
    @Override
    public void generateBalanceNotification(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        List<Account> accounts = user.getAccounts();

        for (Account account : accounts) {
            //check if the balance of an account is below the limit
            if (account.getBalance().compareTo(setLimit) < 0 ){
                String lowBalanceMsg = String.format("SecuBank: Low Balance Alert: %s, Your %s account", account.getBalance(), account.getAccountType().toString());

                //generate notification
                Notification notification = new Notification();
                notification.setMessage(lowBalanceMsg);
                notification.setTimestamp(LocalDateTime.now());
                notification.setRead(false);
                notification.setUser(user);

                notificationRepository.save(notification);
            }
        }
    }

}
