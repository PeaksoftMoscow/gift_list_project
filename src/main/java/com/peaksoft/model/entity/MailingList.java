package com.peaksoft.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mailing_list")
public class MailingList {

    @Id
    @GeneratedValue(generator = "mailing_List_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "mailing_list_gen",sequenceName = "mailing_list_seq",allocationSize = 1)
    private Long id;

    private String header;

    private String image;

    @Size(max = 20000)
    private String text;

    @CreatedDate
    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate createdAt;
}
