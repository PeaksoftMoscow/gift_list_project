package com.peaksoft.controller;

import com.peaksoft.config.jwt.JwTokenUtil;
import com.peaksoft.dto.*;
import com.peaksoft.model.entity.User;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.service.ResetPasswordService;
import com.peaksoft.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
@Tag(name = "Auth  API",
        description = "User with role admin, editor can login and registration ")
public class AutController {

    private final LoginMapper loginMapper;

    private final JwTokenUtil jwTokenUtil;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final ResetPasswordService resetPasswordService;

    @PostMapping("login")
    @Operation(summary = "login",description = "")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody UserRequest request){
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
                    request.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userRepository.findByEmail(token.getName());
            return ResponseEntity.ok().body(loginMapper.loginView(jwTokenUtil.generateToken(user), ValidationType.SUCCESSFUL, user));
        }catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMapper.loginView("", ValidationType.LOGIN_FAILED, null));

        }
    }


    @PostMapping("/registration")
    @Operation(summary = "registration",description = "user can registration ")
    public UserResponse create(@RequestBody UserRequest request) {
        return userService.register(request);
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(@RequestParam("email") String email, HttpServletRequest request){
        return resetPasswordService.processForgotPassword(email,request);
    }

    @PostMapping("/reset_password")
    public AuthResponse resetPassword(@RequestParam String token,@RequestParam String password,@RequestParam String confirmPassword){
        return resetPasswordService.save(token,password,confirmPassword);
    }
}

