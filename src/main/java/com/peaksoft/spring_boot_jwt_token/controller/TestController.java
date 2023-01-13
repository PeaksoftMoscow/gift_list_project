package com.peaksoft.spring_boot_jwt_token.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@PreAuthorize("hasAnyAuthority('EDITOR')")
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
