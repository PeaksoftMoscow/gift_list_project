package com.peaksoft.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.peaksoft.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WishListRequest {


    private String giftName;

    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate holidayDate;

    private String image;

    private String description;

    private String link;

    private User user;

    private Long holidayId;


}
