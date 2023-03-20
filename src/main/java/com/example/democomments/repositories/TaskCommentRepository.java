package com.example.democomments.repositories;

import com.example.democomments.entities.Comment;
import com.example.democomments.entities.Task;
import com.example.democomments.entities.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {
    @Query("select t.comment from TaskComment t where t.task.id = ?1")
    List<Comment> findByTask_Id(Long id);


    @Query("select t.task from TaskComment t where t.comment.id = ?1")
    Task findByComment_Id(Long id);

}
