package com.nvz.secubank.repository;

import com.nvz.secubank.dto.CardDto;
import com.nvz.secubank.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository Class that extends JpaRepository which provides Built-in CRUD operations methods
 * Custom methods
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardNumber(String cardNumber);
    Card findCardByAccount_AccountId(Long accountId);
    List<Card> findCardsByAccount_AccountId(Long accountId);
}
