package com.peaksoft.dto;

import com.peaksoft.model.entity.Category;
import com.peaksoft.model.entity.Charity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SubCategoryRequest {


    private String subcategoryName;

    private Category category;

    private Long categoryId;

    private List<Charity> charities;

}
