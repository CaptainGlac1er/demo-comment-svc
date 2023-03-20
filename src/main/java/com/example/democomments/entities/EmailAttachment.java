package com.example.democomments.entities;

import jakarta.persistence.*;

@Entity
@Table
public class EmailAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn
    private Attachment attachment;

    @OneToOne
    @JoinColumn
    private Email email;
}
