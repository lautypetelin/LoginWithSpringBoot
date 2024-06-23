package com.lautypetelin.LoginWithSpringBoot.services;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService implements IEmailService{

    @Override
    public void sendEmail(String asunto, String to, String body) {

        String addressEmail = "tecnicaturaulp@gmail.com";
        String password = "Prog2023.!";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(addressEmail, password);
            }
        });

        try{

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(addressEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(asunto);
            message.setText(body);

            Transport.send(message);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}