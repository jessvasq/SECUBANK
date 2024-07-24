package com.nvz.secubank.repository;

import com.nvz.secubank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository Class that extends JpaRepository which provides Built-in CRUD operations methods
 * Custom methods
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
    List<Account> findByUser_userId(Long userId);
    List<Account> findByUser_email(String email);
    Account findAccountByAccountNumber(String accountNumber);
}
