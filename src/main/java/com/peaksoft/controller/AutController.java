package com.peaksoft.controller;

import com.peaksoft.dto.UserRequest;
import com.peaksoft.dto.UserResponse;
import com.peaksoft.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AutController {
    private final UserService userService;

    @PostMapping
    public UserResponse create(@RequestBody UserRequest request) {
        return userService.register(request);
    }
}
