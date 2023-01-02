package com.peaksoft.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(generator = "category_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "category_gen", sequenceName = "category_seq", allocationSize = 1)
    private Long id;

    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @JsonIgnore
    private List<SubCategory> subcategories;

    @OneToMany
    @JsonIgnore
    private List<Charity> charities;
}
