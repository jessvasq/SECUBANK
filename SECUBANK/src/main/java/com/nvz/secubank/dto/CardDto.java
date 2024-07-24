package com.nvz.secubank.dto;

import com.nvz.secubank.entity.enumClasses.CardStatus;
import com.nvz.secubank.entity.enumClasses.CardType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;


/**
 * Data Transfer Object used to transfer data between the client and db
 * used to validate data before it's processed, included necessary fields only
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Long cardId;
    @NotEmpty(message = "Please enter card number")
    private String cardNumber;
    private String expirationDate;
    private String securityCode;
   /* @NotEmpty (message = "Enter cardholder name")*/
    private String cardHolderName;
    private LocalDate issueDate;

    @NotNull(message = "Please select Card Type")
    private CardType cardType;

    private CardStatus status = CardStatus.ACTIVE;

    private String accountNumber; //reference to account
}
