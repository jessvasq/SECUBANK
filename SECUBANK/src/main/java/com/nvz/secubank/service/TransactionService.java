package com.nvz.secubank.service;

import com.nvz.secubank.dto.TransactionDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransactionService {
    void makeTransfer(TransactionDto transactionDto);
}
