package com.movie.fullstack.controller;

import com.movie.fullstack.model.ContentMail;
import com.movie.fullstack.model.RegistrationMail;
import com.movie.fullstack.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

@RestController
@CrossOrigin
public class MailController {

    @Autowired
    public MailService mailService;

    @PostMapping("/registration/{receiverMail}")
    public String sendRegistration(@PathVariable String receiverMail, @RequestBody RegistrationMail registrationMail) throws MessagingException {
        Context context = new Context();

        context.setVariable("name", registrationMail.getName());
        context.setVariable("subject", registrationMail.getSubject());

        mailService.sendRegistrationEmail(receiverMail, registrationMail, "registrationMailTemplate", context);
        return "Successfully sent the mail!!";
    }

    @PostMapping("/contentUpdate/{receiverMail}")
    public String sendContentMail(@PathVariable String receiverMail, @RequestBody ContentMail contentMail) throws MessagingException{
        Context context = new Context();

        context.setVariable("name", contentMail.getName());
        context.setVariable("subject", contentMail.getSubject());
        context.setVariable("nameContent", contentMail.getNameContent());
        context.setVariable("content", contentMail.getContent());
        context.setVariable("original_language", contentMail.getOriginal_language());
        context.setVariable("release_date", contentMail.getRelease_date());
        context.setVariable("vote_average", contentMail.getVote_average());
        context.setVariable("trailer", contentMail.getTrailer());
        context.setVariable("overview", contentMail.getOverview());


        mailService.sendContentMail(receiverMail, contentMail, "contentMailTemplate", context);
        return "Successfully sent the mail!!";
    }
}
