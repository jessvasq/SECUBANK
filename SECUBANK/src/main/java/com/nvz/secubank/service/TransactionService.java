package com.nvz.secubank.service;

import com.nvz.secubank.dto.TransactionDto;
import com.nvz.secubank.entity.Account;
import com.nvz.secubank.entity.Notification;
import com.nvz.secubank.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Define methods and abstracts the business logic
 */
@Component
public interface TransactionService {
    void makeTransfer(TransactionDto transactionDto);
    void makeTransferByEmail(TransactionDto transactionDto);
    void processTransfer(Account fromAccount, Account toAccount, TransactionDto transactionDto);
    List<Transaction> getTransactionsByAccountId(Long accountId);
}

