package com.peaksoft.exception;

public class FobidenExceptoin extends RuntimeException {
public FobidenExceptoin(String message) {
	super(message);
}

public FobidenExceptoin(String message, Throwable cause) {
	super(message, cause);
}
}
