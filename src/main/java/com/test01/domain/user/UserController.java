package com.test01.domain.user;

import com.test01.domain.email.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final EmailService emailService;
    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }
    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }
        if(!userCreateForm.getPassword().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("passwordInCorrect", "pwInCorrect", "비밀번호가 일치하지 않습니다");
            return "signup_form";
        }
        this.userService.create(userCreateForm.getUsername(), userCreateForm.getNickname(), userCreateForm.getPassword());

        this.emailService.send(userCreateForm.getUsername(), "가입 축하", "축하요");
        return "redirect:/user/login";
    }
    @GetMapping("/login")
    public String login(LogInIDForm logInForm) {
        return "login_form";
    }

    @PostMapping("/checkId")
    @ResponseBody
    public ResponseEntity<String> checkId(@Valid LogInIDForm logInForm) {
        boolean isValidId = this.userService.checkUsername(logInForm.getUsername());

        if (isValidId) {
            return ResponseEntity.ok("ID is valid"); // ID가 유효할 경우 200 OK 응답 반환
        } else {
            return ResponseEntity.ok("ID is not valid"); // ID가 유효하지 않을 경우 400 Bad Request 응답 반환
        }
    }
}
