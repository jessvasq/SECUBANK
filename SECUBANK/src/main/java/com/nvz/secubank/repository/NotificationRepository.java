package com.nvz.secubank.repository;

import com.nvz.secubank.entity.Notification;
import com.nvz.secubank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository Class that extends JpaRepository which provides Built-in CRUD operations methods
 * Custom methods
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user);
}
