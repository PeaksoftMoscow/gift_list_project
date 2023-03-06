package com.peaksoft.dto;

import com.peaksoft.model.entity.Charity;
import com.peaksoft.model.entity.Subcategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Builder
public class CategoryResponse {

    private Long id;

    private String categoryName;


}
