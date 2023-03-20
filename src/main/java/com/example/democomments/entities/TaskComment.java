package com.example.democomments.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table()
public class TaskComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn()
    private Task task;

    @OneToOne(orphanRemoval = true)
    @JoinColumn()
    private Comment comment;

}
