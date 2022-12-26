package com.peaksoft.springboot_security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentRequest {

    private String name;
    private String surname;
    private String email;
    private int age;
}
