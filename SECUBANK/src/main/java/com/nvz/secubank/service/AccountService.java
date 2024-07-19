package com.nvz.secubank.service;

import com.nvz.secubank.dto.AccountDto;
import com.nvz.secubank.entity.Account;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AccountService {
    void addAccount(AccountDto accountDto);
    AccountDto getAccountById(Long accountId);
    List<AccountDto> getAccountsByUserId(Long userId);
    AccountDto updateAccount(Long accountId, AccountDto accountDto);
    void removeAccountById(Long accountId);
}
