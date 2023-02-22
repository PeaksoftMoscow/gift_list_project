package com.peaksoft.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.peaksoft.model.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishListResponse {

    private Long id;

    private String giftName;

    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate holidayDate;

    private String image;

    private String description;

    private String link;


    private User user;

    private Long holidayId;


}
