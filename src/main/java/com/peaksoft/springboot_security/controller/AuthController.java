//package com.peaksoft.springboot_security.controller;
//
//import com.peaksoft.springboot_security.dto.*;
//import com.peaksoft.springboot_security.entity.User;
//import com.peaksoft.springboot_security.repository.UserRepository;
//import com.peaksoft.springboot_security.security.jwt.JwtTokenUtil;
//import com.peaksoft.springboot_security.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/jwt")
//public class AuthController {
//
//    private final UserService userService;
//    private final UserRepository userRepository;
//    private final JwtTokenUtil jwtTokenUtil;
//    private final LoginMapper loginMapper;
//    private final AuthenticationManager authenticationManager;
//
//
//
//
//
//    @PostMapping("login")
//    public ResponseEntity<LoginResponse> getLogin(@RequestBody UserRequest request){
//        try {
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
//                    request.getPassword());
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getEmail(),
//                            request.getPassword()));
//            User user = userRepository.findByEmail(token.getName()).get();
//            return ResponseEntity.ok().body(loginMapper.loginView(jwtTokenUtil.generateToken(user), ValidationType.SUCCESSFUL,user));
//        }catch (BadCredentialsException ex){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMapper.loginView("",ValidationType.LOGIN_FAILED, null));
//        }
//    }
//
//
//}
