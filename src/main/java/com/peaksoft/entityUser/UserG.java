package com.peaksoft.entityUser;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class UserG {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;

}

