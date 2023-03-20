package com.example.democomments.controller;

import com.example.democomments.dto.CommentDto;
import com.example.democomments.entities.Comment;
import com.example.democomments.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    public Long createComment(@RequestBody CommentDto comment) {
        return this.commentService.createComment(comment).getId();
    }

    @GetMapping(value = "/{id}")
    public CommentDto getComment(@PathVariable("id") Long id) {
        return this.commentService.getCommentById(id);
    }

    @PostMapping(value = "/{id}/reply")
    public Long replyToComment(@PathVariable("id") Long parentId, @RequestBody CommentDto commentDto) {
        return this.commentService.replyToComment(commentDto, parentId).getId();
    }

    @GetMapping(value = "/{id}/replies")
    public List<CommentDto> commentReplies(@PathVariable("id") Long commentId) {
        return this.commentService.getReplies(commentId);
    }

}
