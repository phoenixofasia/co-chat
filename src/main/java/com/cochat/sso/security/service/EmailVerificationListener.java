package com.cochat.sso.security.service;

import com.cochat.sso.security.event.UserRegistrationEvent;
import com.cochat.sso.security.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

    private final JavaMailSender mailSender;
    private final VerificationService verificationService;

    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {
        UserDto user = event.getUser();
        String verificationId = verificationService.createVerification(user.getUsername());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Co-Chat account verification");
        // TODO make url configurable to environment specific
        message.setText("Account activation link: http://localhost:8080/verify/email?id="+verificationId);
        message.setTo(user.getEmail());
        mailSender.send(message);
    }
}
