package com.example.democomments.service;

import com.example.democomments.assemblers.Converter;
import com.example.democomments.dto.CommentDto;
import com.example.democomments.entities.Comment;
import com.example.democomments.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto getCommentById(Long id) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if(comment.isPresent()) {
            CommentDto commentDto = Converter.convertCommentToCommentDto(comment.get());
            commentDto.setReplies(this.commentRepository.countByParent_Id(commentDto.getId()));
            return commentDto;
        }
        return null;
    }

    public CommentDto createComment(CommentDto commentDto) {
        Comment newComment = new Comment();
        newComment.setComment(commentDto.getComment());
        newComment.setUserPoster(commentDto.getUser());
        newComment.setCommentType(commentDto.getType());
        return Converter.convertCommentToCommentDto(this.commentRepository.save(newComment));
    }

    public List<CommentDto> getReplies(Long id) {
        return this.commentRepository.findByParent_Id(id).stream().map(Converter::convertCommentToCommentDto).map(commentDto -> {
            commentDto.setReplies(this.commentRepository.countByParent_Id(commentDto.getId()));
            return commentDto;
        }).collect(Collectors.toList());
    }

    public CommentDto replyToComment(CommentDto commentDto, Long parentId) {
        Comment parentComment = this.commentRepository.getReferenceById(parentId);
        Comment newComment = new Comment();
        newComment.setParent(parentComment);
        newComment.setComment(commentDto.getComment());
        newComment.setUserPoster(commentDto.getUser());
        newComment.setCommentType(commentDto.getType());
        return Converter.convertCommentToCommentDto(this.commentRepository.save(newComment));
    }
}
