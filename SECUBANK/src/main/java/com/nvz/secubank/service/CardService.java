package com.nvz.secubank.service;

import com.nvz.secubank.dto.CardDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CardService {
    void addCard(CardDto cardDto);
    CardDto getCardById(Long cardId);
    List<CardDto> getCardByAccountId(Long accountId);
    CardDto getCardByCardNumber(String cardNumber);
    CardDto updateCard(Long cardId, CardDto cardDto);
    void revokeCard(Long cardId);
}
