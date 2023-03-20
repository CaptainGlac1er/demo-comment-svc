package com.example.democomments.controller;

import com.example.democomments.dto.CommentDto;
import com.example.democomments.entities.Comment;
import com.example.democomments.service.CommentService;
import com.example.democomments.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {
    private final TaskService taskService;
    private final CommentService commentService;

    @Autowired
    public TaskController(TaskService taskService, CommentService commentService) {
        this.taskService = taskService;
        this.commentService = commentService;
    }

    @GetMapping("/{id}/comments")
    public List<CommentDto> getComments(@PathVariable("id") Long taskId) {
        return this.taskService.getCommentsForTask(taskId);
    }

    @PostMapping("/{id}/comment")
    public Long commentOnTask(@RequestBody CommentDto commentDto, @PathVariable("id") Long taskId) {
        CommentDto newCommentDto = this.commentService.createComment(commentDto);
        this.taskService.addCommentToTask(newCommentDto, taskId);
        return newCommentDto.getId();
    }
}
