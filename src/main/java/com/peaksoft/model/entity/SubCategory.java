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
@Table(name = "subcategory")
public class SubCategory {

    @Id
    @GeneratedValue(generator = "subcategory_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "subcategory_gen",sequenceName = "subcategory_seq",allocationSize = 1)
    private Long id;

    private String subCategoryName;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @OneToMany
    @JsonIgnore
    private List<Charity> charities;

}
