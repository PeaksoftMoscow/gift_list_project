package com.peaksoft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
@ExceptionHandler(value = {ApiRequestException.class})
public ResponseEntity<Object> handlerApiRequestException(ApiRequestException e) {
	HttpStatus badRequest = HttpStatus.BAD_REQUEST;
	ApiException apiException = new ApiException(
			e.getMessage(),
			e,
			badRequest,
			ZonedDateTime.now(ZoneId.of("Z"))
	);
	return new ResponseEntity<>(apiException, badRequest);
}

@ExceptionHandler(NotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public ResponseEntity<Object>  handlerApiRequestException(NotFoundException e) {
	
	HttpStatus notFound = HttpStatus.NOT_FOUND;
	ApiException apiException = new ApiException(
			e.getMessage(),
			e,
			notFound, 
			ZonedDateTime.now(ZoneId.of("Z"))
	);
	return new ResponseEntity<>(apiException, notFound);
}

@ExceptionHandler(FobidenExceptoin.class)
//@ResponseStatus(HttpStatus.FORBIDDEN)
public ResponseEntity<Object>  handlerApiRequestException(FobidenExceptoin e) {
	HttpStatus fobiden = HttpStatus.FORBIDDEN;
	ApiException apiException = new ApiException(
			e.getMessage(),
			e,
			fobiden,
			ZonedDateTime.now(ZoneId.of("Z"))
	);
	return new ResponseEntity<>(apiException, fobiden);
	
}
}
