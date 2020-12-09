package com.bridgelabz.fundoonotesapi.fundoonotesapi.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class SendEmail {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String email,String token){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Account Verification Link");
        mailMessage.setText("http://localhost:5000/confirm-account?token=" + token);
        javaMailSender.send(mailMessage);
    }


}
