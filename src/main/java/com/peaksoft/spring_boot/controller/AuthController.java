package com.peaksoft.spring_boot.controller;

import com.peaksoft.spring_boot.dto.*;
import com.peaksoft.spring_boot.entity.User;
import com.peaksoft.spring_boot.repository.UserRepository;
import com.peaksoft.spring_boot.security.jwt.JwTokenUtil;
import com.peaksoft.spring_boot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
@Tag(name = "Student API",
        description = "User with role admin, editor can login and registration ")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwTokenUtil jwTokenUtil;
    private final LoginMapper loginMapper;


    private final AuthenticationManager authenticationManager;

    @PostMapping("login")
    @Operation(summary = "login", description = "we can login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody UserRequest request){
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
                    request.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userRepository.findByEmail(token.getName()).get();
            return ResponseEntity.ok().body(loginMapper.loginView(jwTokenUtil.generateToken(user), ValidationType.SUCCESSFUL, user));
        }catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMapper.loginView("",ValidationType.LOGIN_MAPPER, null));

        }
    }

    @PostMapping("registration")
    @Operation(summary = "registration",description = "we can registration")
    public UserResponse create(@RequestBody UserRequest request){
        return userService.createUser(request);
    }
}
