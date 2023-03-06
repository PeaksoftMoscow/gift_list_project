package com.peaksoft.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.peaksoft.model.entity.ClothingSize;
import com.peaksoft.model.entity.ShoeSize;
import com.peaksoft.model.entity.enums.Country;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProfileRequest {
    @Enumerated(EnumType.STRING)
    private Country country;

    private String firstName;
    private String lastName;
    private String email;
    private Long number;
    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate dateOfBirth;
    private List<ClothingSize> clothingSizes;
    private List<ShoeSize> shoeSizes;
    private String importantToKnow;
    private String hobbies;
}
