package com.nvz.secubank.controller;

import com.nvz.secubank.dto.TransactionDto;
import com.nvz.secubank.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransactionController {
    //inject service
    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public String transferFunds(@ModelAttribute("transaction") @Valid TransactionDto transactionDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("transactionDto", transactionDto);
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            return "transaction";
        }
        transactionService.makeTransfer(transactionDto);
        return "redirect:/home";
    }

    @GetMapping("/transfer")
    public String showTransactionForm(Model model) {
        model.addAttribute("transactionDto", new TransactionDto());
        return "transaction";
    }

}