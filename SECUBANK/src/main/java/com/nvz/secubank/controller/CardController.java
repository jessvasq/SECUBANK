package com.nvz.secubank.controller;

import com.nvz.secubank.dto.AccountDto;
import com.nvz.secubank.dto.CardDto;
import com.nvz.secubank.entity.Account;
import com.nvz.secubank.entity.Card;
import com.nvz.secubank.entity.User;
import com.nvz.secubank.repository.CardRepository;
import com.nvz.secubank.service.AccountService;
import com.nvz.secubank.service.CardService;
import com.nvz.secubank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages incoming requests and handles responses
 * Binds incoming requests data to objects or DTOs for validation
 * Selects and returns a view
 */
@Controller
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountService accountService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Handles post requests
     * @param card holds the form data
     * @param result holds results of the validation process
     * @param model used to pass data to the view
     * @return string that represents name of the view to render
     */
    @PostMapping("/users/card/{id}")
    public String addCard(@PathVariable("id") Long id, @ModelAttribute("card") @Valid CardDto card, BindingResult result, Model model) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            model.addAttribute("card", card);
            return "card";
        }
        // Get the logged-in user's email from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        cardService.addCard(card, userEmail, id);
        return "redirect:/users/accounts";
    }

    /**
     * Handles get requests to the method
     * @param model used to pass data to the view
     * @return string that represents name of the view to render
     */
    @GetMapping("/users/card/{id}")
    public String showCard(@PathVariable("id") Long id, Model model) {
        Card card = cardService.getCardById(id);

        if (card == null){
            model.addAttribute("card", new CardDto());
            System.out.println("error");
            return "home";
        }

        model.addAttribute("cardDetails", card);
        return "card";
    }

//    @GetMapping("/users/cards/{id}")
//    public String showCards(Model model) {
//
//
//
//        //get account ids
//        List<Long> accountIds = accounts.stream().map(AccountDto :: getAccountId).collect(Collectors.toList());
//
//        List<Card> cards = cardService.getCards(accountIds);
//        model.addAttribute("cards", cards);
//        return "card";
//    }
}
