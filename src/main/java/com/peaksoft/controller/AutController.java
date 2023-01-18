package com.peaksoft.controller;

import com.peaksoft.config.jwt.JwTokenUtil;
import com.peaksoft.dto.*;
import com.peaksoft.model.User;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.service.ResetPasswordService;
import com.peaksoft.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class AutController {

    private final LoginMapper loginMapper;

    private final JwTokenUtil jwTokenUtil;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final ResetPasswordService resetPasswordService;




    @PostMapping("/register")
    public UserResponse create(@RequestBody UserRequest request) {
        return userService.register(request);
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(@RequestParam("email") String email, HttpServletRequest request){
        return resetPasswordService.processForgotPassword(email,request);
    }

    @PostMapping("/reset_password")
    public AuthResponse resetPassword(@RequestParam String token,@RequestParam String password,@RequestParam String currentPassword){
        return resetPasswordService.save(token,password,currentPassword);
    }
}

