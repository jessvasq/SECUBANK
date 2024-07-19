package com.nvz.secubank.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long accountId;
    @NotEmpty(message = "Please enter account number")
    private String accountNumber;
    @NotEmpty
    private BigDecimal balance;
    @NotEmpty
    private String currency;
    @NotEmpty
    private BigDecimal interestRate;
    @NotEmpty
    private Long userId; //reference to the user
}