package com.peaksoft.halidayCode.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.peaksoft.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class HolidayRequest {

private String holidayName;

private String image;
@JsonFormat(pattern = "yyyy.MM.dd")
private LocalDate date;
private User user;
private Long user_id;

}
