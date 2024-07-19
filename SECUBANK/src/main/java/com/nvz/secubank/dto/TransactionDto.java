package com.nvz.secubank.dto;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private BigDecimal amount;
    @NotEmpty
    private LocalDateTime date;
    @NotEmpty
    private String description;
    @NotEmpty
    private String status;
    @NotEmpty
    private String transactionType;
    @NotEmpty
    private Long accountId; //reference to account
}
