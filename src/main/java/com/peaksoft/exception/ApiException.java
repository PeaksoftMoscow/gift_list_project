package com.peaksoft.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@Getter@Setter
public class ApiException {
private final String message;
private HttpStatus httpStatus;
private final ZonedDateTime tiestamp;

public ApiException(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime tiestamp) {
	this.message = message;
	this.httpStatus = httpStatus;
	this.tiestamp = tiestamp;
}


}
