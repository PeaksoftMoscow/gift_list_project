package com.peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
@GeneratedValue(generator = "holiday_gen", strategy = GenerationType.SEQUENCE)
@SequenceGenerator(name = "holiday_gen", sequenceName = "holiday_seq", allocationSize = 1)
private Long id;

private String holidayName;

private String image;

@JsonFormat(pattern = "yyyy.MM.dd")
private LocalDate date;

@OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,
			CascadeType.MERGE})
@JoinTable(name = "holiday_wishList", joinColumns = @JoinColumn(name = "holidays_id")
			, inverseJoinColumns = @JoinColumn(name = "wishList_id"))
@JsonIgnore
private List<WishList> wishList;

@Transient
private Long wish_id;

@ManyToOne
@JoinColumn(name = "user_id")
@JsonIgnore
private User user;
@Transient
private Long user_id;


}
