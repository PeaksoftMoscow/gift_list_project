package com.peaksoft.controller;

import com.peaksoft.dto.AuthResponse;
import com.peaksoft.dto.UserRequest;
import com.peaksoft.dto.UserResponse;
import com.peaksoft.service.ResetPasswordService;
import com.peaksoft.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class AutController {
    private final UserService userService;

    private final ResetPasswordService resetPasswordService;

    @PostMapping
    public UserResponse create(@RequestBody UserRequest request) {
        return userService.register(request);
    }

    @PostMapping("forgot_password")
    public String processForgotPassword(@RequestParam("email") String email, HttpServletRequest request){
        return resetPasswordService.processForgotPassword(email,request);
    }

    @PostMapping("reset_password")
    public AuthResponse resetPassword(@RequestParam String token,@RequestParam String password,@RequestParam String currentPassword){
        return resetPasswordService.save(token,password,currentPassword);
    }
}

