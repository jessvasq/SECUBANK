package com.nvz.secubank.service.impl;

import com.nvz.secubank.dto.CardDto;
import com.nvz.secubank.entity.Account;
import com.nvz.secubank.entity.Card;
import com.nvz.secubank.entity.User;
import com.nvz.secubank.entity.enumClasses.AccountType;
import com.nvz.secubank.entity.enumClasses.CardStatus;
import com.nvz.secubank.entity.enumClasses.CardType;
import com.nvz.secubank.repository.AccountRepository;
import com.nvz.secubank.repository.CardRepository;
import com.nvz.secubank.repository.UserRepository;
import com.nvz.secubank.service.CardService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

/**
 * Override CardService methods and provide business logic
 */
@Slf4j
@Service
@Transactional
public class CardServiceImpl implements CardService {

    public static final int ACCOUNT_NUMBER_LENGTH = 12;
    private final SecureRandom random = new SecureRandom();

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    /**
     * generates a random security code and persist Card data to the db
     * @param cardDto Takes input data from the client side
     */
    @Override
    public void addCard(CardDto cardDto, String userEmail, Long accountId) {

        //find account by account numbers
        Account account = accountRepository.findAccountByAccountId(accountId);


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

        //generate unique card number
        String uniqueCardNumber = generateCardNumber();

        //set card Type
        if (account.getAccountType() == AccountType.CHECKING) {
            cardDto.setCardType(CardType.DEBIT);
        } else if (account.getAccountType() == AccountType.SAVINGS) {
            System.out.println("Card cannot be created for a savings account");
            return;
        }else {
            cardDto.setCardType(CardType.CREDIT);
        }

        //Create new Card entity
        Card card = new Card();
        card.setCardNumber(uniqueCardNumber);
        card.setExpirationDate(yearMonthStr);
        card.setSecurityCode(randomSecurityCode);
        //get User details, user first and Last Name to set CardHolderName
        card.setCardHolderName(accountHolderName);
        card.setIssueDate(LocalDate.now());
        card.setStatus(CardStatus.ACTIVE);

        //associate the card to the account
        card.setAccount(account);

        // persist data to the db
        cardRepository.save(card);

    }

    @Override
    public Card getCardById(Long accountId) {
        return cardRepository.findCardByAccount_AccountId(accountId);
    }

    @Override
    public List<Card> getCards(Long accountId) {
        return cardRepository.findCardsByAccount_AccountId(accountId);
    }

    /**
     * Helper method to generate a unique card number
     * Method looks by account number ensuring that the account number is unique
     * @return account number
     */
    public String generateCardNumber() {
        String randomCardNumber;
        Card cardObj;

        do {
            StringBuilder sb= new StringBuilder();
            for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++){
                sb.append(random.nextInt(10));
            }
            randomCardNumber = sb.toString();
            cardObj = cardRepository.findByCardNumber(randomCardNumber);
        } while (cardObj != null);
        return randomCardNumber;
    }

}
