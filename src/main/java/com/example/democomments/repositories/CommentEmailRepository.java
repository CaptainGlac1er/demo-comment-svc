package com.example.democomments.repositories;

import com.example.democomments.entities.Comment;
import com.example.democomments.entities.CommentEmail;
import com.example.democomments.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentEmailRepository extends JpaRepository<CommentEmail, Long> {
    @Query("select c.email from CommentEmail c where c.comment.id = :id")
    Email findByComment_Id(@Param("id") Long id);

    @Query("select c.comment from CommentEmail c where c.email.id = :id")
    Comment findByEmail_Id(@Param("id") Long id);


}
