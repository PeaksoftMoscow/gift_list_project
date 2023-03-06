package com.peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.entity.enums.ComplaintsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Complaints {

    @Id
    @GeneratedValue(generator = "complaints_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "complaints_gen",sequenceName = "complaints_seq",allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ComplaintsType complaintsType;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "with_list_id")
    private WishList wishList;

    @ManyToOne
    @JoinColumn(name = "charity_id")
    @JsonIgnore
    private Charity charity;

    @OneToMany(mappedBy = "complaints", cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();

}
