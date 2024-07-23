package com.nvz.secubank.service.impl;

import com.nvz.secubank.dto.TransactionDto;
import com.nvz.secubank.entity.Account;
import com.nvz.secubank.entity.Notification;
import com.nvz.secubank.entity.Transaction;
import com.nvz.secubank.entity.User;
import com.nvz.secubank.entity.enumClasses.AccountType;
import com.nvz.secubank.repository.AccountRepository;
import com.nvz.secubank.repository.TransactionRepository;
import com.nvz.secubank.repository.UserRepository;
import com.nvz.secubank.service.NotificationService;
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
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void makeTransfer(TransactionDto transactionDto) {
        Account fromAccount = accountRepository.findByAccountNumber(transactionDto.getFromAccountNumber());
        Account toAccount = accountRepository.findByAccountNumber(transactionDto.getToAccountNumber());
        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid account number");
        }
        processTransfer(fromAccount, toAccount, transactionDto);
    }

    @Override
    public void makeTransferByEmail(TransactionDto transactionDto){
        Account fromAccount = accountRepository.findByAccountNumber(transactionDto.getFromAccountNumber());
        //find external user object by its email
        User user = userRepository.findByEmail(transactionDto.getToUserEmail());

        if (fromAccount == null) {
            throw new IllegalArgumentException("Invalid account number");
        }

        if( user == null ){
            throw new IllegalArgumentException("Invalid user email");
        }

        //return account where accountType equals to 'Checking'
        String accountNumber = null;
        List<Account> userAccounts = user.getAccounts();
        for (Account userAccount : userAccounts) {
            if (userAccount.getAccountType() == AccountType.CHECKING){
                accountNumber = userAccount.getAccountNumber();
                System.out.println("accountNumber FOUND: " + accountNumber + "for user email: " + userAccount.getUser().getEmail() + "account balance " + userAccount.getBalance());
                break;
            }
        }
        Account toAccount = accountRepository.findByAccountNumber(accountNumber);
        processTransfer(fromAccount, toAccount, transactionDto);
    }

    // External Transfers
    @Override
    public void processTransfer(Account fromAccount, Account toAccount, TransactionDto transactionDto) {
        // perform transaction using the compareTo method as both values are BigDecimal. Check if the balance is greater or equal than the amount
        if (fromAccount.getBalance().compareTo(transactionDto.getAmount()) >= 0){
            fromAccount.setBalance(fromAccount.getBalance().subtract(transactionDto.getAmount()));
            toAccount.setBalance(toAccount.getBalance().add(transactionDto.getAmount()));
            System.out.println("successful transfer");
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
        transaction.setTransactionType(transactionDto.getTransactionType());
        // Associate transaction with the fromAccount
        transaction.setAccount(fromAccount);

        transactionRepository.save(transaction);

        // Add the transaction to both accounts, create association
        fromAccount.getTransactions().add(transaction);
        toAccount.getTransactions().add(transaction);

        //Generate notifications
        String fromAccountMsg = String.format("SecuBank: %s, you sent: %s, Memo: %s", fromAccount.getUser().getEmail(), transaction.getAmount(), transaction.getDescription());
        String toAccountMsg = String.format("SecuBank: %s, sent you: %s, Memo: %s", toAccount.getUser().getEmail(), transaction.getAmount(), transaction.getDescription());

        // check if user is making an internal transaction --> show the notification once
        if (fromAccount.getUser().getEmail().equals(toAccount.getUser().getEmail())) {
            notificationService.saveNotification(fromAccount.getUser().getEmail(), fromAccountMsg);

            //generate low balance notification if needed
            notificationService.generateBalanceNotification(fromAccount.getUser().getEmail());

        } else {
            notificationService.saveNotification(fromAccount.getUser().getEmail(), fromAccountMsg);
            notificationService.saveNotification(toAccount.getUser().getEmail(), toAccountMsg);

            //generate low balance notification if needed
            notificationService.generateBalanceNotification(fromAccount.getUser().getEmail());
            notificationService.generateBalanceNotification(toAccount.getUser().getEmail());
        }
        // persist changes
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccount_AccountId(accountId);
    }

    private TransactionDto convertEntityToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId(transaction.getTransactionId());
        transactionDto.setFromAccountNumber(transaction.getFromAccountNumber());
        transactionDto.setToAccountNumber(transaction.getToAccountNumber());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setDescription(transaction.getDescription());
        transactionDto.setStatus(transaction.getStatus());
        transactionDto.setTransactionType(transaction.getTransactionType());

        return transactionDto;
    }
}
