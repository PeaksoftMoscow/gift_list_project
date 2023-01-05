package com.peaksoft.confix;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class EmailMesage {
private String to;
private String subject;
private String text;

public EmailMesage() {
}

public EmailMesage(String to, String subject, String text) {
	this.to = to;
	this.subject = subject;
	this.text = text;
}
}
