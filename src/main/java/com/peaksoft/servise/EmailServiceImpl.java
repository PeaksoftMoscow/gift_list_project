package com.peaksoft.servise;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl  {
private final  JavaMailSender mailSender;


public EmailServiceImpl(JavaMailSender mailSender) {
	this.mailSender = mailSender;
}

public void sendSimpleMessage(String to, String subject, String text) {
	SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();
	simpleMailMessage.setFrom("ryskeldi.osmonaliev@mail.ru");
	simpleMailMessage.setTo(to);
	simpleMailMessage.setSubject(subject);
	simpleMailMessage.setText(text);
	
	this.mailSender.send(simpleMailMessage);
}
}
