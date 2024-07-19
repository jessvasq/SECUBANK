package com.nvz.secubank.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipientDto {
    private Long recipientID;
    @NotEmpty(message = "Please enter your First Name")
    private String firstName;
    @NotEmpty(message = "Please enter your Last Name")
    private String lastName;
    @NotEmpty(message = "Please enter account number")
    private String accountNumber;
    @NotEmpty(message = "Please enter bank name")
    private String bankName;
    private String ifscCode;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private Long userId;
}
