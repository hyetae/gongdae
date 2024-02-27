package com.jy.gongdae.controller;

import com.jy.gongdae.service.UserService;
import com.jy.gongdae.dto.UserCreateForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm){
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "redirect:/";
        }

        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다.");
            return "redirect:/";
        }

        userService.create(userCreateForm.getUser_id(), userCreateForm.getPassword1(), userCreateForm.getBusinessCategory());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/";
    }
}