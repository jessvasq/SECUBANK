package com.nvz.secubank.dto;

import com.nvz.secubank.entity.enumClasses.TransactionStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @NotNull
    private BigDecimal amount;
    private LocalDateTime date;
    @NotEmpty
    private String description;
    private TransactionStatus status = TransactionStatus.PENDING;

}
