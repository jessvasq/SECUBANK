package com.nvz.secubank.controller;

import com.nvz.secubank.dto.UserDto;
import com.nvz.secubank.dto.UserDtoUpdate;
import com.nvz.secubank.entity.User;
import com.nvz.secubank.repository.UserRepository;
import com.nvz.secubank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import java.util.List;

/**
 * Manages incoming requests and handles responses
 * Binds incoming requests data to objects or DTOs for validation
 * Selects and returns a view
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    /**
     * Maps get requests with url 'users/new' to the method
     * @param model used to pass data to the view
     * @return string that represents name of the view to render
     */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    /**
     * Handles post requests
     * @param userDto holds the form data
     * @param bindingResult holds results of the validation process
     * @param model used to pass data to the view
     * @return string that represents name of the view to render
     */
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

    /**
     * Handles post requests
     * @param model used to pass data to the view
     * @return string that represents name of the view to render
     */
    @GetMapping("/accounts")
    public String accounts(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "account";
    }

    /**
     * Maps get requests with url 'users/new' to the method
     * @param model used to pass data to the view
     * @return string that represents name of the view to render
     */
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    /**
     * Handles post requests
     * @param userDto holds the form data
     * @param bindingResult holds results of the validation process
     * @param model used to pass data to the view
     * @return
     */
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
        return "redirect:/profile";
    }

    /**
     * Maps get requests with url 'users/new' to the method
     * @param model used to pass data to the view
     * @return string that represents name of the view to render
     */
    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userService.findByEmail(userEmail);

        model.addAttribute("user", user);
        return "profile";
    }

    /**
     * Maps get requests with url 'users/new' to the method
     * @param model used to pass data to the view
     * @return string that represents name of the view to render
     */
    @GetMapping("/updateUser")
    public String showUpdateUserForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "updateForm";
    }

}
