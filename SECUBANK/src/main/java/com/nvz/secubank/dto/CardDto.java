package com.nvz.secubank.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Long cardId;
    @NotEmpty(message = "Please enter card number")
    private String cardNumber;
    @NotEmpty
    private YearMonth expirationDate;
    @NotEmpty
    private String securityCode;
    @NotEmpty (message = "Enter cardholder name")
    private String cardHolderName;
    @NotEmpty
    private LocalDate issueDate;
    @NotEmpty
    private String cardType;
    @NotEmpty
    private String cardStatus;
    @NotEmpty
    private Long accountId;
}
