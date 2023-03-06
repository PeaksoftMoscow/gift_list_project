package com.peaksoft.dto;


import com.peaksoft.entity.enums.RoleE;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleE role;
}
