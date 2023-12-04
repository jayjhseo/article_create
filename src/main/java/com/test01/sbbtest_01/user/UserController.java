package com.test01.sbbtest_01.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/signup")
    public String signup() {
        return "signup_form";
    }
    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }
        this.userService.create(userCreateForm.getUsername(), userCreateForm.getNickname(), userCreateForm.getPassword());
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}
