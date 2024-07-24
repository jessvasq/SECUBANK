package com.nvz.secubank.dto;
import com.nvz.secubank.entity.enumClasses.AccountType;
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
public class AccountDto {
    private Long accountId;
    @NotEmpty(message = "Please enter account number")
    private String accountNumber;
    @NotNull
    private BigDecimal balance;
    @NotEmpty
    private String currency;
    @NotNull
    private BigDecimal interestRate;
    private LocalDateTime createdAt;

    @NotNull(message = "Please select an account type")
    private AccountType accountType;

    private BigDecimal prevBalance;

    private String userEmail; //reference to the user
}