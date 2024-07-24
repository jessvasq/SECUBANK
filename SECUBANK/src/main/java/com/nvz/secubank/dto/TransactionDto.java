package com.nvz.secubank.dto;

import com.nvz.secubank.entity.enumClasses.TransactionStatus;
import com.nvz.secubank.entity.enumClasses.TransactionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Data Transfer Object used to transfer data between the client and db
 * used to validate data before it's processed, included necessary fields only
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long transactionId;

    @NotEmpty (message = "Please provide Account to perform transfer")
    private String fromAccountNumber;
    @NotEmpty(message = "Please provide Account to perform transfer")
    private String toAccountNumber;

    private String toUserEmail;

    @NotNull
    private BigDecimal amount;
    private LocalDateTime date;
    @NotEmpty
    private String description;
    private TransactionStatus status = TransactionStatus.PENDING;
    private TransactionType transactionType = TransactionType.TRANSFER;
}
