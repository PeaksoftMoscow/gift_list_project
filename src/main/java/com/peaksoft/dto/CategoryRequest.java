package com.peaksoft.dto;

import com.peaksoft.model.entity.Charity;
import com.peaksoft.model.entity.Subcategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class CategoryRequest {

    private String categoryName;

    private List<Subcategory> subcategories;

    private List<Charity> charities;

}
