package com.nvz.secubank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Data Transfer Object used to transfer data between the client and db
 * used to validate data before it's processed, included necessary fields only
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    @NotEmpty(message = "Please enter your First Name")
    private String firstName;
    @NotEmpty(message = "Please enter your Last Name")
    private String lastName;
    @NotEmpty(message = "Please create a username")
    private String userName;
    @NotEmpty(message = "Please enter a password")
    private String password;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Please enter your address")
    private String address;
    @NotEmpty(message = "Please enter your phone number")
    private String phoneNumber;
    private String profilePicture;
    private String language;
    private LocalDateTime createdAt;
    private ZoneId timeZone;
    private String ssn;


    public boolean isAdminRegistration(){
        return email.endsWith("@admin.com");
    }

}
