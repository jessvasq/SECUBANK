package com.nvz.secubank.service;

import com.nvz.secubank.dto.TransactionDto;
import com.nvz.secubank.entity.Account;
import org.springframework.stereotype.Component;


@Component
public interface TransactionService {
    void makeTransfer(TransactionDto transactionDto);
    void makeTransferByEmail(TransactionDto transactionDto);
    void processTransfer(Account fromAccount, Account toAccount, TransactionDto transactionDto);
}
