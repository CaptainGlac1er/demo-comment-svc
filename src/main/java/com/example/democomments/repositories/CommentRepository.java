package com.example.democomments.repositories;

import com.example.democomments.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByParent_Id(Long id);

    Integer countByParent_Id(Long id);
}
