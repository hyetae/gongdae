package com.jy.gongdae.controller;

import com.jy.gongdae.dto.UserResponseDto;
import com.jy.gongdae.dto.UserModificationRequestDto;
import com.jy.gongdae.service.UserService;
import com.jy.gongdae.dto.UserRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ResponseBody
@Controller
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public Long signup(@RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    @GetMapping("/login")
    public String login() {
        return "";
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable(name="id") Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable(name="id") Long id, @RequestBody UserModificationRequestDto userModificationRequestDto) {
        return userService.updateUser(id, userModificationRequestDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable(name="id") Long id){
        userService.deleteUser(id);
        return id;
    }
}