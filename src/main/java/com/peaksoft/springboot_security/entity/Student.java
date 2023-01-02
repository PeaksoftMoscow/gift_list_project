package com.peaksoft.springboot_security.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen",
            sequenceName = "student_seq",
            allocationSize = 1)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private int age;
    @NotNull
    private Boolean isActive = true;
    private Boolean deleted = false;
    @CreatedDate
    private LocalDate created;
}

