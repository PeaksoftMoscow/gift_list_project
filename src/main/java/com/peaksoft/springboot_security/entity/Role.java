package com.peaksoft.springboot_security.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_gen",
            sequenceName = "role_seq",
            allocationSize = 1)
    private Long id;
    private String roleName;

    @ManyToMany(targetEntity = User.class,
            cascade = {CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    private List<User> users;


    @Override
    public String getAuthority() {
        return roleName;
    }
}