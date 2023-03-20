package com.example.democomments.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comment_email")
@Getter
@Setter
public class CommentEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "email_id")
    private Email email;

}
