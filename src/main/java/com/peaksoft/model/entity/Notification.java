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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(generator = "notification_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "notification_gen", sequenceName = "notification_seq", allocationSize = 1)
    private Long id;

    private String giftName;

    private Long giftId;

    private boolean isRead;

    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate createdAt;

    @ManyToMany(cascade = {CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    private List<User> receivers = new ArrayList<>();

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonIgnore
    private Charity charity;

    @ManyToOne
    private Complaints complaints;

    @ManyToOne
    @JsonIgnore
    private WishList wishList;

}
