package com.peaksoft.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reset_password_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(generator = "password_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "password_seq",sequenceName = "password_gen",allocationSize = 1)
    private Long id;
    private String token;
    private LocalDateTime expirationTime;

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    private User user;
}
