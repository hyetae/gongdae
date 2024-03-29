package com.jy.gongdae.controller;

import com.jy.gongdae.dto.UserReadDto;
import com.jy.gongdae.dto.UserUpdateDto;
import com.jy.gongdae.service.UserService;
import com.jy.gongdae.dto.UserCreateForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ResponseBody
@Controller
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public Long signup(@RequestBody UserCreateForm userCreateForm) {
        return userService.createUser(userCreateForm);
    }

    @GetMapping("/login")
    public String login() {
        return "";
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable(name="id") Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable(name="id") Long id, @RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(id, userUpdateDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable(name="id") Long id){
        userService.deleteUser(id);
        return id;
    }
}