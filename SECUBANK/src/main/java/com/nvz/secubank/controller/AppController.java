package com.nvz.secubank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/footer")
    public String footer() {
        return "footer";
    }

    @GetMapping("/nav")
    public String nav() {
        return "navBar";
    }

//    @GetMapping("/profile")
//    public String profile() {
//        return "profile";
//    }

    @GetMapping("/userAccount")
    public String userAccount() {
        return "account";
    }

    @GetMapping("/processTransfer")
    public String processTransferPage() {
        return "transactions";
    }

}
