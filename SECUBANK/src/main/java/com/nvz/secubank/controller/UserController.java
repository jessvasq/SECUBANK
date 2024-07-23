package com.nvz.secubank.controller;

import com.nvz.secubank.dto.UserDto;
import com.nvz.secubank.dto.UserDtoUpdate;
import com.nvz.secubank.entity.User;
import com.nvz.secubank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping ("/register/save")
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model) {
        User userExist = userService.findByEmail(userDto.getEmail());
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDto);
            return "register";
        }

        userService.saveUser(userDto);
        return "redirect:/users/accounts";
    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/home")
//    public String home() {
//        return "home";
//    }

    @GetMapping("/accounts")
    public String accounts(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "account";
    }

//    @GetMapping("/")
//    public String index() {
//        return "home";
//    }
//
//    @GetMapping("/footer")
//    public String footer() {
//        return "footer";
//    }
//
//    @GetMapping("/nav")
//    public String nav() {
//        return "navBar";
//    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/updateUser")
    public String updateUserProfile(@ModelAttribute("user") @Valid UserDtoUpdate userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDto);
            return "updateForm";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userService.findByEmail(userEmail);
        Long userID = user.getUserId();

        userService.updateUser(userID, userDto);
        return "redirect:/home"; // Redirect to the list of users or user profile page
    }

    @GetMapping("/updateUser")
    public String showUpdateUserForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "updateForm";
    }

}
