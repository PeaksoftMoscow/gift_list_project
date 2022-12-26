package com.peaksoft.springboot_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@PreAuthorize("hasAuthority('ADMIN')")
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}
