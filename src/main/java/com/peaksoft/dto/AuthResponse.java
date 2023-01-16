package com.peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String authorities;
    private String jwtToken;
    private String message;
}
