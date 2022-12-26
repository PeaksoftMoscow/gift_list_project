package com.peaksoft.model.entity;

import com.peaksoft.model.User;
import com.peaksoft.model.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    Long id;
    private Animals animals;
    private Clothes clothes;
    private Electronic electronic;
    private HouseAndGarden houseAndGarden;
    private Transport transport;
    private Shoes shoes;
    private School school;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
