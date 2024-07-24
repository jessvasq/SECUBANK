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


/**
 * Manages incoming requests and handles responses
 * Binds incoming requests data to objects or DTOs for validation
 * Selects and returns a view
 */
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    /**
     * Maps get requests to the method
     * @param model object used to pass data from the controller to the view
     * @return string that represents name of the view to render
     */
    @GetMapping("/notifications")
    public String showNotification(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        List<Notification> notificationList = notificationService.getNotificationsByUserEmail(userEmail);
        model.addAttribute("notifications", notificationList);

        return "notificationsList";
    }

    /**
     * Handles post requests
     * @param id from the url
     * @return string that represents name of the view to render
     */
    @PostMapping("/notifications/read/{id}")
    public String readNotification(@PathVariable Long id) {
        notificationService.markNotificationAsRead(id);
        return "redirect:/users/accounts";
    }
}
