package com.nvz.secubank.service.impl;

import com.nvz.secubank.dto.CardDto;
import com.nvz.secubank.entity.Account;
import com.nvz.secubank.entity.Card;
import com.nvz.secubank.entity.User;
import com.nvz.secubank.repository.AccountRepository;
import com.nvz.secubank.repository.CardRepository;
import com.nvz.secubank.repository.UserRepository;
import com.nvz.secubank.service.CardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Override CardService methods and provide business logic
 */
@Service
@Transactional
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Takes input data from the client side, generates a random security code and persist Card data to the db
     * @param cardDto
     */
    @Override
    public void addCard(CardDto cardDto) {
        //get the account number from Account object and create card

        //find account by account number
        Account account = accountRepository.findByAccountNumber(cardDto.getAccountNumber());

        //get User details, user first and Last Name to set CardHolderName
        User user = userRepository.findByEmail(account.getUser().getEmail());
        String accountHolderName = user.getFirstName() + " " + user.getLastName();

        //set expiration date using YearMonth
        YearMonth yearObj = YearMonth.now();
        YearMonth yearObj2 = yearObj.plusYears(5);
        String yearMonthStr = yearObj2.toString();
        System.out.println("Year: " + yearObj2);


        // Generate random numbers for the security code
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(10));
        }
        String randomSecurityCode = sb.toString();

        //Create new Card entity
        Card card = new Card();
        card.setCardNumber(cardDto.getCardNumber());
        card.setExpirationDate(yearMonthStr);
        card.setSecurityCode(randomSecurityCode);
        //get User details, user first and Last Name to set CardHolderName
        card.setCardHolderName(accountHolderName);

        card.setIssueDate(LocalDate.now());
        card.setCardType(cardDto.getCardType());
        card.setStatus(cardDto.getStatus());

        //associate the card to the account
        card.setAccount(account);

        // persist data to the db
        cardRepository.save(card);

    }

}
