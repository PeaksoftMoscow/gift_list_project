package com.peaksoft.halidayCode.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
@Builder
public class HolidayResponse {
private Long id;

private String holidayName;

private String image;

@JsonFormat(pattern = "yyyy.MM.dd")
private LocalDate date;
//private User user;
//private Long user_id;
}
