package com.example.democomments.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column()
    private String comment;

    @Column()
    private String commentType;

    @Column()
    private Timestamp posted = Timestamp.from(Instant.now());

    @JoinColumn()
    @OneToOne()
    private Comment parent;

    @Column()
    private String userPoster;

}
