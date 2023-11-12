package com.example.buysell.controllers;

import com.example.buysell.model.User;
import com.example.buysell.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/register")
    public String register(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "register";
    }

    @PostMapping("/register")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует!");
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/users/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        return "user-info";
    }
}
