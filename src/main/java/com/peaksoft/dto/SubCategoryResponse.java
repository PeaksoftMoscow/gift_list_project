package com.peaksoft.dto;

import com.peaksoft.model.entity.Category;
import com.peaksoft.model.entity.Charity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SubCategoryResponse {

    private Long id;

    private String subcategoryName;

    private Category category;

    private List<Charity> charities;
}
