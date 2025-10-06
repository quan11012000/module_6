package com.example.crudkafka.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nguyentienquan12a4@gmail.com");  // Gmail gửi đi
        message.setTo(to);                        // Gmail nhận
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
