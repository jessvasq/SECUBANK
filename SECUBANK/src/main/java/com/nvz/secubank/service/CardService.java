package com.nvz.secubank.service;

import com.nvz.secubank.dto.AccountDto;
import com.nvz.secubank.dto.CardDto;
import com.nvz.secubank.entity.Account;
import org.springframework.stereotype.Component;

/**
 * Define methods and abstracts the business logic
 */
@Component
public interface CardService {
    void addCard(CardDto cardDto);
}
