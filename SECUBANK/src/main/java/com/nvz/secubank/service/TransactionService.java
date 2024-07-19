package com.nvz.secubank.service;

import com.nvz.secubank.dto.TransactionDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransactionService {
    void saveTransaction(TransactionDto transactionDto);
    List<TransactionDto> getTransactionsByAccount(Long accountId);
    TransactionDto getTransactionById(Long transactionId);
    void cancelTransaction(Long transactionId);
    TransactionDto updateTransaction(Long transactionId, TransactionDto transactionDto);
}
