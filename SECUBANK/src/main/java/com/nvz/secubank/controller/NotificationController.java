package com.nvz.secubank.controller;

import com.nvz.secubank.entity.Notification;
import com.nvz.secubank.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notifications")
    public String showNotification(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        List<Notification> notificationList = notificationService.getNotificationsByUserEmail(userEmail);
        model.addAttribute("notifications", notificationList);

        return "notificationsList";
    }

    @PostMapping("/notifications/read")
    public String readNotification(@PathVariable Long id) {
        notificationService.markNotificationAsRead(id);
        return "redirect:/notifications";
    }
}
