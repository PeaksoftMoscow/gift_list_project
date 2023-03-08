package com.peaksoft.controller;

import com.peaksoft.service.BookingServise;
import com.peaksoft.config.jwt.JwTokenUtil;
import com.peaksoft.dto.*;
import com.peaksoft.model.User;
import com.peaksoft.model.entity.Booking;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.service.ResetPasswordService;
import com.peaksoft.service.UserService;
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
public class AutController {

    private final LoginMapper loginMapper;

    private final JwTokenUtil jwTokenUtil;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final ResetPasswordService resetPasswordService;
    private final BookingServise bookingServise;


    @PostMapping("login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody UserRequest request) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
                    request.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userRepository.findByEmail(token.getName()).get();
            return ResponseEntity.ok().body(loginMapper.loginView(jwTokenUtil.generateToken(user), ValidationType.SUCCESSFUL, user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMapper.loginView("", ValidationType.LOGIN_FAILED, null));

        }
    }

    @PostMapping("/registration")
    public UserResponse create(@RequestBody UserRequest request) {
        return userService.register(request);
    }

    @PostMapping("book/{id}")
    public Booking booking(@PathVariable Long id) {
        return bookingServise.bookCharity(id);
    }

    @PostMapping("bookWish/{id}")
    public String bookingWishlist(@PathVariable Long id) {
        return bookingServise.bookWishlist(id);
    }
}
