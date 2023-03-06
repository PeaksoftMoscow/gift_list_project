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
@Table(name = "subcategories")
public class Subcategory {

    @Id
    @GeneratedValue(generator = "subcategory_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "subcategory_gen",sequenceName = "subcategory_seq",allocationSize = 1)
    private Long id;

    @Column(name = "subcategory_name")
    private String subcategoryName;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private List<Charity> charities;

}
