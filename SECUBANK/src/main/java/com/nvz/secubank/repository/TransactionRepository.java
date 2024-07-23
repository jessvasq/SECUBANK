package com.nvz.secubank.repository;

import com.nvz.secubank.entity.Transaction;
import com.nvz.secubank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTransactionId(long transactionId);
    List<Transaction> findByAccount_User(User user);
    List<Transaction> findByAccount_AccountId(Long accountId);
}
