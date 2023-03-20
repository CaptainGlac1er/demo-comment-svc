package com.example.democomments.dto;

import com.example.democomments.entities.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String comment;
    private String type;
    private Timestamp timestamp;
    private String user;

    private Integer replies;

}
