package com.nvz.secubank.repository;

import com.nvz.secubank.entity.Account;
import com.nvz.secubank.entity.User;
import com.nvz.secubank.entity.enumClasses.AccountType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;


   @Test
    void testFindByAccountNumber() {
       // Arrange
       BigDecimal balance = new BigDecimal("1000000");
       BigDecimal interest = new BigDecimal("0.03");
       AccountType accountType = AccountType.CHECKING;

       User newUser = new User();
       newUser.setFirstName("Patrick");
       newUser.setLastName("Doe");
       newUser.setUserName("patrickDoe3");
       newUser.setEmail("patrick4@test.com");
       newUser.setPassword("password");
       newUser.setPhoneNumber("1234567890");
       newUser.setAddress("NYC");
       newUser.setLanguage("english");
       newUser.setSsn("225-22-2233");
       newUser.setCreatedAt(LocalDateTime.now());
       newUser.setTimeZone(ZoneId.systemDefault());

       userRepository.save(newUser);

       Account account = new Account();
       account.setAccountNumber("233344444");
       account.setBalance(balance);
       account.setCurrency("USD");
       account.setInterestRate(interest);
       account.setCreatedAt(LocalDateTime.now());
       account.setAccountType(accountType);
       account.setUser(newUser);

       accountRepository.save(account);

       // Act
       Account foundAccount = accountRepository.findByAccountNumber("233344444");

       // Assert
       assertEquals(account.getAccountNumber(), foundAccount.getAccountNumber());
       // Optionally assert other properties
       assertEquals(account.getBalance(), foundAccount.getBalance());
       assertEquals(account.getCurrency(), foundAccount.getCurrency());
       assertEquals(account.getInterestRate(), foundAccount.getInterestRate());
       assertEquals(account.getAccountType(), foundAccount.getAccountType());

    }


    @Test
    void testFindByUser_email() {
       String email = "patrick@test.com";
       List <Account> foundAccounts = accountRepository.findByUser_email(email);
       for (Account account : foundAccounts) {
           assertEquals(account.getUser().getEmail(), email);
       }
    }
}