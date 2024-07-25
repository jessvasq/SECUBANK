package com.nvz.secubank.service;

import com.nvz.secubank.dto.AccountDto;
import com.nvz.secubank.dto.CardDto;
import com.nvz.secubank.entity.Account;
import com.nvz.secubank.entity.Card;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Define methods and abstracts the business logic
 */
@Component
public interface CardService {
    void addCard(CardDto cardDto, String userEmail, Long accountId);
    Card getCardById(Long accountId);
    List<Card> getCards(Long accountId);
}
