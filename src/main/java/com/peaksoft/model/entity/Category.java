package com.peaksoft.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(generator = "category_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "category_gen", sequenceName = "category_seq", allocationSize = 1)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(fetch = FetchType.LAZY, cascade = {REFRESH,PERSIST,MERGE,DETACH}, mappedBy = "category")
    @JsonIgnore
    private List<Subcategory> subcategories;

    @OneToMany(fetch = FetchType.LAZY, cascade = {DETACH,PERSIST,MERGE,REFRESH})
    @JsonIgnore
    private List<Charity> charities;
}
