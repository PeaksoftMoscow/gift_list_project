package com.peaksoft.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.model.User;
import com.peaksoft.model.entity.enums.CharityStatus;
import com.peaksoft.model.entity.enums.Condition;
import com.peaksoft.model.entity.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Table(name = "charity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Charity {

    @Id
    @GeneratedValue(generator = "charity_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "charity_gen", sequenceName = "charity_seq", allocationSize = 1)
    private Long id;

    private String charityName;

    private String image;

    @Size(max = 5000)
    private String description;

    private LocalDate charityDate;

    private boolean isBlocked;

    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Enumerated(EnumType.STRING)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Transient
    private Long userId;


    @ManyToOne(cascade = {DETACH,PERSIST,MERGE,REFRESH} ,fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Transient
    private Long categoryId;


    @ManyToOne(cascade = {CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "subcategory_id")
    private Subcategory subCategory;

    @Transient
    private Long subCategoryId;


    @OneToOne(mappedBy = "charity",cascade = CascadeType.ALL)
    private Booking booking;

    @Enumerated(EnumType.STRING)
    private CharityStatus charityStatus;

    @OneToMany(mappedBy = "charity",cascade = CascadeType.ALL)
    private List<Complaints> complaints;

    @OneToMany(mappedBy = "charity",cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();
}
