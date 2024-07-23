package com.nvz.secubank.controller;

import com.nvz.secubank.dto.AccountDto;
import com.nvz.secubank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/users/new")
    public String showNewAccountForm(Model model) {
        AccountDto accountDto = new AccountDto();
        model.addAttribute("accountDto", accountDto);
        return "newaccount";
    }

    @PostMapping("/users/new")
    public String createAccount(  @ModelAttribute("accountDto") @Valid AccountDto accountDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("accountDto", accountDto);
            result.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            System.out.println("error");
            return "newaccount";
        }
        // Get the logged-in user's email from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        accountDto.setUserEmail(userEmail);

        accountService.addAccount(accountDto);
        return "redirect:/users/accounts";
    }

    @GetMapping("/users/accounts")
    public String showAccounts(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        model.addAttribute("userEmail", userEmail);

        List<AccountDto> accounts = accountService.getAccountsByEmail(userEmail);
        model.addAttribute("accounts", accounts);
        return "home";
    }

    @GetMapping("/accounts/{id}")
    public String showAccountDetails(@PathVariable("id") Long id, Model model) {
        AccountDto accountDto = accountService.getAccountById(id);
        model.addAttribute("accountDto", accountDto);
        return "account";
    }
}
