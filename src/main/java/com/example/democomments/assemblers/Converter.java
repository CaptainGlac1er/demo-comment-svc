package com.example.democomments.assemblers;

import com.example.democomments.dto.CommentDto;
import com.example.democomments.dto.EmailDto;
import com.example.democomments.entities.Comment;
import com.example.democomments.entities.Email;
import com.example.democomments.entities.EmailRecipient;

import java.util.stream.Collectors;

public class Converter {
    public static CommentDto convertCommentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(comment.getComment());
        commentDto.setUser(comment.getUserPoster());
        commentDto.setId(comment.getId());
        commentDto.setType(comment.getCommentType());
        commentDto.setTimestamp(comment.getPosted());
        return commentDto;
    }

    public static EmailDto convertEmailToEmailDto(Email email) {
        EmailDto emailDto = new EmailDto();
        emailDto.setId(email.getId());
        emailDto.setEmailContents(email.getEmailContents());
        emailDto.setEmailRecipients(email.getEmailRecipientList().stream().map(EmailRecipient::getEmailAddress).collect(Collectors.toList()));
        emailDto.setTimestamp(email.getPosted());
        return emailDto;
    }
}
