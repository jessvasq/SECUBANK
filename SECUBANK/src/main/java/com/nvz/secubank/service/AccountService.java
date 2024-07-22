package com.nvz.secubank.service;

import com.nvz.secubank.dto.AccountDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AccountService {
    void addAccount(AccountDto accountDto);
    List<AccountDto> getAccountsByUserId(Long userId);
    void removeAccountById(Long accountId);
}
