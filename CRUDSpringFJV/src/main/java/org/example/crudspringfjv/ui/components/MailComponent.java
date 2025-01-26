package org.example.crudspringfjv.ui.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class MailComponent {


    @Value(value = "${app.subject}")
    private String subject;


    private final JavaMailSender javaMailSender;

    public MailComponent(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);

        message.setSubject(this.subject);
        message.setText(body);
        try {
            javaMailSender.send(message);
        } catch (MailException e) {

        }

    }
}
