package com.peaksoft.dto;

import com.peaksoft.model.entity.enums.Country;
import com.peaksoft.model.entity.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter @Setter
@Builder
public class UserResponse  {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

}
