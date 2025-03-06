package com.movie.fullstack.service;



import com.movie.fullstack.model.ContentMail;
import com.movie.fullstack.model.RegistrationMail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MailService {

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired TemplateEngine templateEngine;

    @Value("$(MYFLIX Registration)")
    private String registrationMailSender;

    @Value("$(MYFLIX Content Update)")
    private String contentMailSender;

    @Async
    public void sendContentMail(String receiverMail, ContentMail contentMail, String template, Context context) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String htmlContent = templateEngine.process(template, context);

        helper.setTo(receiverMail);
        helper.setSubject(contentMail.getSubject());
        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }

    @Async
    public void sendRegistrationEmail(String receiverMail, RegistrationMail registrationMail, String template, Context context) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String htmlContent = templateEngine.process(template, context);

        helper.setTo(receiverMail);
        helper.setSubject(registrationMail.getSubject());
        helper.setText(htmlContent, true);

        javaMailSender.send(message);

    }

//    public String readHtmlFile(String filePath) throws IOException {
//        Path path = Paths.get(filePath);
//        return Files.readString(path, StandardCharsets.UTF_8);
//    }
}

