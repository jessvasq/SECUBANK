package com.nvz.secubank.service;

import com.nvz.secubank.dto.AccountDto;
import com.nvz.secubank.dto.CardDto;
import com.nvz.secubank.entity.Account;
import org.springframework.stereotype.Component;

@Component
public interface CardService {
    void addCard(CardDto cardDto);
    CardDto getCardByCardNumber(String cardNumber);
    CardDto updateCard(Long cardId, CardDto cardDto);
    void revokeCard(Long cardId);
}
