package com.nvz.secubank.service.impl;

import com.nvz.secubank.dto.TransactionDto;
import com.nvz.secubank.entity.Account;
import com.nvz.secubank.entity.Transaction;
import com.nvz.secubank.repository.AccountRepository;
import com.nvz.secubank.repository.TransactionRepository;
import com.nvz.secubank.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public void makeTransfer(TransactionDto transactionDto) {
        Account fromAccount = accountRepository.findByAccountNumber(transactionDto.getFromAccountNumber());
        Account toAccount = accountRepository.findByAccountNumber(transactionDto.getToAccountNumber());
        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid account number");
        }

        // perform transaction using the compareTo method as both values are BigDecimal. Check if the balance is greater or equal than the amount
        if (fromAccount.getBalance().compareTo(transactionDto.getAmount()) >= 0){
            fromAccount.setBalance(fromAccount.getBalance().subtract(transactionDto.getAmount()));
            toAccount.setBalance(toAccount.getBalance().add(transactionDto.getAmount()));
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }

        // Create new Transaction entity
        Transaction transaction = new Transaction();
        transaction.setFromAccountNumber(fromAccount.getAccountNumber());
        transaction.setToAccountNumber(toAccount.getAccountNumber());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDate(LocalDateTime.now());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setStatus(transactionDto.getStatus());
        // Associate transaction with the fromAccount
        transaction.setAccount(fromAccount);

        transactionRepository.save(transaction);

        // Add the transaction to both accounts, create association
        fromAccount.getTransactions().add(transaction);
        toAccount.getTransactions().add(transaction);

        // persist changes
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }


    private TransactionDto convertEntityToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId(transaction.getTransactionId());
        transactionDto.setFromAccountNumber(transaction.getFromAccountNumber());
        transactionDto.setToAccountNumber(transaction.getToAccountNumber());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setDescription(transaction.getDescription());
        transactionDto.setStatus(transaction.getStatus());

        return transactionDto;
    }
}
