package com.nvz.secubank.controller;

import com.nvz.secubank.dto.CardDto;
import com.nvz.secubank.service.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Manages incoming requests and handles responses
 * Binds incoming requests data to objects or DTOs for validation
 * Selects and returns a view
 */
@Controller
public class CardController {

    @Autowired
    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Handles post requests
     * @param card holds the form data
     * @param bindingResult holds results of the validation process
     * @param model used to pass data to the view
     * @return string that represents name of the view to render
     */
    @PostMapping("/users/card")
    public String addCard(@ModelAttribute("card") @Valid CardDto card, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("error");
            model.addAttribute("card", card);
            return "card";
        }

        cardService.addCard(card);
        return "redirect:/users/accounts";
    }

    /**
     * Handles get requests to the method
     * @param model used to pass data to the view
     * @return string that represents name of the view to render
     */
    @GetMapping("/users/card")
    public String showCards(Model model) {
        CardDto card = new CardDto();
        model.addAttribute("card", card);
        return "card";
    }
}
