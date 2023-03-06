package com.peaksoft.dto;

import com.peaksoft.model.User;
import com.peaksoft.model.entity.Category;
import com.peaksoft.model.entity.Subcategory;
import com.peaksoft.model.entity.enums.CharityStatus;
import com.peaksoft.model.entity.enums.Condition;
import com.peaksoft.model.entity.enums.Country;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CharityRequest {

    private String charityName;

    private String image;

    private String description;

    private LocalDate charityDate;

    private boolean isBlocked;

    private Condition condition;

    private Country country;

    private User user;

    private Long userId;

    private Category category;

    private Long categoryId;

    private CharityStatus charityStatus;

    private Subcategory subCategory;

    private Long subCategoryId;


}
