package com.peaksoft.dto;

import com.peaksoft.model.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
