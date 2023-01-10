package com.peaksoft.spring_boot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class UserRequest {

    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDate created;
}
