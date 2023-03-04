package com.peaksoft.dto;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendResponse {

    private Long id;

    private String firstName;

    private String lastName;

    @Email
    private String email;



}
