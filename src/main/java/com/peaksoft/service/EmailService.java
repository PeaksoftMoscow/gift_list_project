package com.peaksoft.service;


import com.peaksoft.dto.Mail;
import lombok.RequiredArgsConstructor;



import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    public void sendEmail(Mail mail,String url){
        Properties properties = System.getProperties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.host", "localhost");
        properties.setProperty("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("saidibakassatybaldyev@gmail.com", "skeifkeifjwe0");
            }
        });
    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("saidibakassatybaldyev@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail.getTo()));
        message.setSubject(message.getSubject());
        message.setText(url);
        Transport.send(message);
    }catch (MessagingException ex){
        throw new RuntimeException(ex);
    }
    }
}