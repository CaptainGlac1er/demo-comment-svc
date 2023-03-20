package com.example.democomments.controller;

import com.example.democomments.dto.EmailDto;
import com.example.democomments.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("email")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/{user}/send/{commentId}")
    public EmailDto sendCommentAsEmail(@PathVariable("commentId") Long commentId, @PathVariable("user") String user, @RequestBody() List<String> emailAddresses) {
        return this.emailService.sendCommentAsEmail(user, commentId, emailAddresses);
    }

    @PostMapping("/{user}/reply/{emailId}")
    public EmailDto emailReply(@PathVariable("emailId") Long emailId, @PathVariable("user") String user, @RequestBody() EmailDto emailDto) {
        return this.emailService.createReplyEmailComment(user, emailId, emailDto);
    }

    @GetMapping("/{commentId}")
    public EmailDto getEmailFromComment(@PathVariable("commentId") Long commentId) {
        return this.emailService.getEmailForCommentId(commentId);
    }

}
