package com.peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.entity.enums.Country;

import com.peaksoft.entity.enums.RoleE;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    private String email;

    @Column(name = "phone_number")
    private Long number;

    private String image;

    @Column(name = "country")
    private Country country;

    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Size(max = 50000)
    private String hobbies;

    @Size(max = 5000)
    @Column(name = "important_to_know")
    private String importantToKnow;

    @OneToMany(fetch = FetchType.LAZY, cascade = ALL, mappedBy = "user")
    private List<WishList> wishList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "receivers")
    private List<Notification> notificationList = new ArrayList<>();

    @OneToMany(cascade = ALL, mappedBy = "user")
    @JsonIgnore
    private List<Holiday> holiday;

    @OneToMany(cascade = ALL, mappedBy = "user")
    private List<Complaints> complaints;

    @OneToMany(cascade = ALL, mappedBy = "user")
    private List<Charity> charity;

    @OneToMany(cascade = ALL, mappedBy = "userId")
    private List<Booking> bookings;

    @OneToMany(cascade = ALL, mappedBy = "user")
    private List<ClothingSize> clothingSizes;

    @OneToMany(cascade = ALL, mappedBy = "user")
    private List<ShoeSize> shoeSizes;


    @ManyToMany(fetch = FetchType.LAZY, cascade ={PERSIST,REFRESH,MERGE})
    @JoinColumn(name = "friends")
    @JsonIgnore
    private List<User> friends = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {PERSIST,REFRESH,MERGE})
    @JsonIgnore
    private List<User> requestToFriends = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private RoleE roleES;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = new LinkedList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(roleES.getAuthority()));

        return grantedAuthorities;
    }

    public void addRequestToFriend(User user) {
        if (requestToFriends == null) {
            requestToFriends = new ArrayList<>();
        }
        requestToFriends.add(user);
    }

    public void acceptToFriend(User user) {
        if (friends == null) {
            friends = new ArrayList<>();
        }
        friends.add(user);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    }




