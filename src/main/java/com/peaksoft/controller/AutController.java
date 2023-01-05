package com.peaksoft.controller;

import com.peaksoft.confix.EmailMesage;
import com.peaksoft.dto.UserRequest;
import com.peaksoft.dto.UserResponse;
import com.peaksoft.servise.EmailServiceImpl;
import com.peaksoft.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AutController {
private final UserService userService;
private final EmailServiceImpl emailService;

@PostMapping
public UserResponse create(@RequestBody UserRequest request) {
	return userService.register(request);
}

@PostMapping("/setn-email")
public ResponseEntity sentEmail(@RequestBody EmailMesage emailMesage) {
this.emailService.sendSimpleMessage(emailMesage.getTo(),emailMesage.getSubject(), emailMesage.getText());
return ResponseEntity.ok("Sucess");
}
}


