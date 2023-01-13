package com.peaksoft.spring_boot_jwt_token.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
