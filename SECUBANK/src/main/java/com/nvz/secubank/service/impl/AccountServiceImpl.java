package com.nvz.secubank.service.impl;

import com.nvz.secubank.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nvz.secubank.dto.AccountDto;
import com.nvz.secubank.entity.Account;
import com.nvz.secubank.entity.User;
import com.nvz.secubank.repository.AccountRepository;
import com.nvz.secubank.repository.UserRepository;
import com.nvz.secubank.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Override AccountService methods and provide business logic
 */
@Slf4j
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private NotificationService notificationService;

    /**
     * Saves an account to the DB
     * @param accountDto
     */
    @Override
    public void addAccount(AccountDto accountDto) {
        logger.info("Adding account for user email: {}", accountDto.getUserEmail());
        //find user by email
        User user = userRepository.findByEmail(accountDto.getUserEmail());
        if (user == null) {
            logger.error("User not found for email: {}", accountDto.getUserEmail());
            throw new UsernameNotFoundException("User not found with email: " + accountDto.getUserEmail());
        }

        // Create a new Account entity
        Account account = new Account();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setBalance(accountDto.getBalance());
        account.setCurrency(accountDto.getCurrency());
        account.setInterestRate(accountDto.getInterestRate());
        account.setCreatedAt(LocalDateTime.now());
        account.setAccountType(accountDto.getAccountType());

        account.setPrevBalance(accountDto.getPrevBalance());

        account.setUser(user); //associate the account with the user

        // Save account to the db
        accountRepository.save(account);

        //Generate low balance notification if needed
        notificationService.generateBalanceNotification(user.getEmail());

        logger.info("Account successfully added for user email: {}", accountDto.getUserEmail());
    }

    /**
     * Gets users by userId and returns a list of Accounts associated to the user
     * @param userId
     * @return
     */
    @Override
    public List<AccountDto> getAccountsByUserId(Long userId) {
        List<Account> accounts = accountRepository.findByUser_userId((userId));

        return accounts.stream().map((account) -> convertEntityToDto(account)).collect(Collectors.toList());
    }

    /**
     * Removes account by its id
     * @param accountId
     */
    @Override
    public void removeAccountById(Long accountId) {
        accountRepository.deleteById(accountId);

    }

    /**
     * Fetches accounts by email and returns a list of accounts associated to the provided email
     * @param email
     * @return
     */
    @Override
    public List<AccountDto> getAccountsByEmail(String email) {
      List<Account> accounts = accountRepository.findByUser_email(email);
        return accounts.stream().map((account) -> convertEntityToDto(account)).collect(Collectors.toList());
    }

    /**
     * Fetches and returns an account by ist id
     * @param accountId
     * @return
     */
    @Override
    public AccountDto getAccountById(Long accountId) {
        return convertEntityToDto(accountRepository.findById(accountId).get());
    }

    /**
     * Converts a Account entity to AccountDTO
     * @param account
     * @return
     */
    private AccountDto convertEntityToDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(account.getAccountId());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setBalance(account.getBalance());
        accountDto.setCurrency(account.getCurrency());
        accountDto.setInterestRate(account.getInterestRate());
        accountDto.setCreatedAt(account.getCreatedAt());
        accountDto.setAccountType(account.getAccountType());

        accountDto.setPrevBalance(account.getPrevBalance());

        return accountDto;
    }
}
