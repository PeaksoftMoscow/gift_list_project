package com.peaksoft.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {

    private String firstName;

    private String lastName;

    @Email
    private String email;

}
