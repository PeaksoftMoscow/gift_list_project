package com.peaksoft.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booked")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(generator = "booking_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "booking_gen", sequenceName = "booking_seq", allocationSize = 1)
    private Long id;

    @CreatedDate
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userId;

    @OneToOne
    @JoinColumn(name = "charity_id")
    private Charity charity;

    @OneToOne
    @JoinColumn(name = "wish_list_id")
    @JsonIgnore
    private WishList wishList;
}
