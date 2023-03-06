package com.peaksoft.dto;

import com.peaksoft.model.entity.enums.CharityStatus;
import com.peaksoft.model.entity.enums.Condition;
import com.peaksoft.model.entity.enums.Country;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CharityResponse {

    private Long id;

    private String charityName;

    private String image;

    private String description;

    private LocalDate charityDate;

    private boolean isBlocked;

    private Condition condition;

    private Country country;

    private Long userId;

    private Long categoryId;

    private CharityStatus charityStatus;

    private Long subCategoryId;

}
