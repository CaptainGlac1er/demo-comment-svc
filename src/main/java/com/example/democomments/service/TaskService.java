package com.example.democomments.service;

import com.example.democomments.assemblers.Converter;
import com.example.democomments.dto.CommentDto;
import com.example.democomments.entities.TaskComment;
import com.example.democomments.repositories.CommentEmailRepository;
import com.example.democomments.repositories.CommentRepository;
import com.example.democomments.repositories.TaskCommentRepository;
import com.example.democomments.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final CommentRepository commentRepository;
    private final TaskCommentRepository taskCommentRepository;
    private final TaskRepository taskRepository;
    @Autowired
    public TaskService(CommentRepository commentRepository, TaskCommentRepository taskCommentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskCommentRepository = taskCommentRepository;
        this.taskRepository = taskRepository;
    }

    public List<CommentDto> getCommentsForTask(Long taskId) {
        return this.taskCommentRepository.findByTask_Id(taskId).stream().map(Converter::convertCommentToCommentDto).map(commentDto -> {
            commentDto.setReplies(this.commentRepository.countByParent_Id(commentDto.getId()));
            return commentDto;
        }).collect(Collectors.toList());
    }

    public void addCommentToTask(CommentDto commentDto, Long taskId) {
        TaskComment taskComment = new TaskComment();
        taskComment.setComment(this.commentRepository.getReferenceById(commentDto.getId()));
        taskComment.setTask(this.taskRepository.getReferenceById(taskId));
        this.taskCommentRepository.save(taskComment);
    }
}
