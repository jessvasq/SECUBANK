package com.nvz.secubank.service;

import com.nvz.secubank.dto.AccountDto;
import com.nvz.secubank.entity.Account;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Define methods and abstracts the business logic
 */
@Component
public interface AccountService {
    void addAccount(AccountDto accountDto);
    List<AccountDto> getAccountsByUserId(Long userId);
    void removeAccountById(Long accountId);
    List<AccountDto> getAccountsByEmail(String email);
    AccountDto getAccountById(Long accountId);
    Account getAccountByAccountNumber(String accountNumber);
}
