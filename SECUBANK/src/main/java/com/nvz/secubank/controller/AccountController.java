package com.nvz.secubank.controller;

import com.nvz.secubank.dto.AccountDto;
import com.nvz.secubank.entity.Transaction;
import com.nvz.secubank.service.AccountService;
import com.nvz.secubank.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Manages incoming requests and handles responses
 * Binds incoming requests data to objects or DTOs for validation
 * Selects and returns a view
 */
@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private final TransactionService transactionService;

    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    /**
     * Maps get requests with url 'users/new' to the method
     * @param model object used to pass data from the controller to the view
     * @return string that represents name of the view to render
     */
    @GetMapping("/users/new")
    public String showNewAccountForm(Model model) {
        AccountDto accountDto = new AccountDto();
        //add accountDto attribute which can be used to bind/display data
        model.addAttribute("accountDto", accountDto);
        return "newaccount";
    }

    /**
     * @param accountDto holds the form data
     * @param result holds results of the validation process
     * @param model used to pass data to the view
     * '@valid' validates object and ensures it complies with constraints defined in the DTO class
     * @return string that represents name of the view to render
     */
    @PostMapping("/users/new")
    public String createAccount(  @ModelAttribute("accountDto") @Valid AccountDto accountDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("accountDto", accountDto);
            result.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            System.out.println("error");
            return "home";
        }
        // Get the logged-in user's email from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        accountDto.setUserEmail(userEmail);

        accountService.addAccount(accountDto);
        return "redirect:/users/accounts";
    }

    /**
     * Maps get requests to the method
     * @param model object used to pass data from the controller to the view
     * @return string that represents name of the view to render
     */
    @GetMapping("/users/accounts")
    public String showAccounts(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        model.addAttribute("userEmail", userEmail);

        List<AccountDto> accounts = accountService.getAccountsByEmail(userEmail);
        model.addAttribute("accounts", accounts);
        return "home";
    }

    /**
     * @param id from the url
     * @param model object used to pass data from the controller to the view
     * @return string that represents name of the view to render
     */
    @GetMapping("/accounts/{id}")
    public String showAccountDetails(@PathVariable("id") Long id, Model model) {
        AccountDto accountDto = accountService.getAccountById(id);
        model.addAttribute("accountDto", accountDto);
        List<Transaction> transactions = transactionService.getTransactionsByAccountId(id);
        model.addAttribute("transactions", transactions);
        return "account";
    }
}
