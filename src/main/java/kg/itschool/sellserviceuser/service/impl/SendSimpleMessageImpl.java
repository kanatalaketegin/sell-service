package kg.itschool.sellserviceuser.service.impl;

import kg.itschool.sellserviceuser.service.SendSimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendSimpleMessageImpl implements SendSimpleMessage {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMessage(String email, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ya.kana1986@gmail.com");
        message.setTo(email);
        message.setSubject("Код подтверждения");
        message.setText("Ваш код подтверждения: " + text);
        mailSender.send(message);
    }
}
