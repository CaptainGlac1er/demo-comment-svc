package com.example.democomments.service;

import com.example.democomments.assemblers.Converter;
import com.example.democomments.dto.EmailDto;
import com.example.democomments.entities.*;
import com.example.democomments.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService {
    private final CommentEmailRepository commentEmailRepository;
    private final CommentRepository commentRepository;
    private final TaskCommentRepository taskCommentRepository;
    private final EmailRepository emailRepository;
    private final EmailRecipientRepository emailRecipientRepository;

    @Autowired
    public EmailService(
            CommentEmailRepository commentEmailRepository,
            CommentRepository commentRepository,
            TaskCommentRepository taskCommentRepository,
            EmailRepository emailRepository,
            EmailRecipientRepository emailRecipientRepository
    ) {
        this.commentEmailRepository = commentEmailRepository;
        this.commentRepository = commentRepository;
        this.taskCommentRepository = taskCommentRepository;
        this.emailRepository = emailRepository;
        this.emailRecipientRepository = emailRecipientRepository;
    }

    public EmailDto getEmailForCommentId(Long commentId){
        return Converter.convertEmailToEmailDto(this.commentEmailRepository.findByComment_Id(commentId));
    }

    public EmailDto sendCommentAsEmail(String user, Long commentId, List<String> emails) {

        Comment comment = this.commentRepository.findById(commentId).get();
        return this.createEmailComment(comment.getComment(), emails, user, commentId, false);

    }

    public EmailDto createEmailComment(String emailContents, List<String> emails, String userWhoSent, Long relatedCommentId, Boolean withParent) {
        Email newEmail = new Email();
        newEmail.setEmailContents(emailContents);


        List<EmailRecipient> emailRecipientList = emails.stream().map(email -> {
            EmailRecipient emailRecipient = new EmailRecipient();
            emailRecipient.setEmailAddress(email);
            emailRecipient.setEmail(newEmail);
            // this.emailRecipientRepository.save(emailRecipient);
            return emailRecipient;
        }).collect(Collectors.toList());

        newEmail.setEmailRecipientList(emailRecipientList);
        this.emailRepository.save(newEmail);

        Comment newComment = new Comment();
        newComment.setComment(emailContents);
        newComment.setCommentType("EMAIL");
        newComment.setUserPoster(userWhoSent);
        if(withParent){
            newComment.setParent(this.commentRepository.getReferenceById(relatedCommentId));
        }

        this.commentRepository.save(newComment);



        CommentEmail newEmailComment = new CommentEmail();
        newEmailComment.setComment(newComment);
        newEmailComment.setEmail(newEmail);

        EmailDto emailDto = Converter.convertEmailToEmailDto(this.commentEmailRepository.save(newEmailComment).getEmail());
        if(!withParent) {
            TaskComment newTaskComment = new TaskComment();
            newTaskComment.setTask(this.taskCommentRepository.findByComment_Id(relatedCommentId));
            newTaskComment.setComment(newComment);
            this.taskCommentRepository.save(newTaskComment);
        }

        return emailDto;
    }


    public EmailDto createReplyEmailComment(String user, Long emailId, EmailDto emailDto) {
        Long commentId = this.commentEmailRepository.findByEmail_Id(emailId).getId();
        return this.createEmailComment(emailDto.getEmailContents(), emailDto.getEmailRecipients(), user, commentId, true);
    }
}
