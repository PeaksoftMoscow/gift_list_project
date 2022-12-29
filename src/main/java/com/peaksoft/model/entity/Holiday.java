package com.peaksoft.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "holiday")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Holiday {

    @Id
    @GeneratedValue(generator = "holiday_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "holiday_gen",sequenceName = "holiday_seq",allocationSize = 1)
    private Long id;

    private String holidayName;

    private String image;

    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate date;

    @OneToMany(cascade = {CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH})
    @JsonIgnore
    private List<WishList> wishList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
