package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Async
    public void sendWelcomeEmail(String toEmail, String employeeName) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject("Welcome to Employee Management System");

        message.setText(
                "Hello " + employeeName + ",\n\n" +
                "Your registration has been completed successfully.\n\n" +
                "Thank you for joining our company.\n\n" 
        );

        mailSender.send(message);

    }
    
    //save 

}