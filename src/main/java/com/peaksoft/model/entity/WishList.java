package com.peaksoft.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.model.User;
import com.peaksoft.model.entity.enums.CharityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishList {

@Id
@GeneratedValue(generator = "wish_list_gen", strategy = GenerationType.SEQUENCE)
@SequenceGenerator(name = "wish_list_gen", sequenceName = "wish_list_seq", allocationSize = 1)
private Long id;

@Column(name = "gist_name")
private String giftName;

@Column(name = "holiday_date")
private LocalDate holidayDate;

private String image;

@Size(max = 20000)
private String description;

private String link;
@Enumerated(EnumType.STRING)
private CharityStatus charityStatus;

private LocalDate created;

@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST})
@JoinTable(name = "holiday_wishList",joinColumns = @JoinColumn(name = "wishList_id")
		, inverseJoinColumns = @JoinColumn(name = "holidays_id"))
@JsonIgnore
private Holiday holidays;

@ManyToOne
@JoinColumn(name = "user_id")
@JsonIgnore
private User user;

@OneToOne(mappedBy = "wishList", cascade = CascadeType.ALL)
private Booking booking;

@OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL)
private List<Notification> notifications = new ArrayList<>();

@OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL)
private List<Complaints> complaints;
}
