package com.nvz.secubank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Manages incoming requests and handles responses
 * Binds incoming requests data to objects or DTOs for validation
 * Selects and returns a view
 */
@Controller
public class AppController {
    /**
     * Maps get requests to the method
     * @return string that represents name of the view to render
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Maps get requests to the method
     * @return string that represents name of the view to render
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * Maps get requests to the method
     * @return string that represents name of the view to render
     */
    @GetMapping("/")
    public String index() {
        return "home";
    }

    /**
     * Maps get requests to the method
     * @return string that represents name of the view to render
     */
    @GetMapping("/footer")
    public String footer() {
        return "footer";
    }

    /**
     * Maps get requests to the method
     * @return string that represents name of the view to render
     */
    @GetMapping("/nav")
    public String nav() {
        return "navBar";
    }

    /**
     * Maps get requests to the method
     * @return string that represents name of the view to render
     */
    @GetMapping("/userAccount")
    public String userAccount() {
        return "account";
    }

    /**
     * Maps get requests to the method
     * @return string that represents name of the view to render
     */
    @GetMapping("/processTransfer")
    public String processTransferPage() {
        return "transactions";
    }

}
