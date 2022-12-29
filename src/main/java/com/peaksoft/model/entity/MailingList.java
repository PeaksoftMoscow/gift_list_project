package com.peaksoft.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mailingList")
public class MailingList {

    @Id
    @GeneratedValue(generator = "mailing_List_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "mailing_list_gen",sequenceName = "mailing_list_seq",allocationSize = 1)
    private Long id;

    private String email;

    private String header;

    @Size(max = 20000)
    private String text;

    @CreatedDate
    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate createdAt;
}
