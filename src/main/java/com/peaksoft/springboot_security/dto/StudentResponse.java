package com.peaksoft.springboot_security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class StudentResponse {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private Boolean isActive;
    private LocalDate created;
}
