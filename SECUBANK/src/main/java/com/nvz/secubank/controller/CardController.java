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

@Controller
public class CardController {

    @Autowired
    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

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


    @GetMapping("/users/card")
    public String showCards(Model model) {
        CardDto card = new CardDto();
        model.addAttribute("card", card);
        return "card";
    }
}
