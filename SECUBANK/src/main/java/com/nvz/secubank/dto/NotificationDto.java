package com.nvz.secubank.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Data Transfer Object used to transfer data between the client and db
 * used to validate data before it's processed, included necessary fields only
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Long notificationID;

    @NotEmpty
    private String message;

    private LocalDateTime timestamp;

    @NotEmpty
    private String notificationType;

}
