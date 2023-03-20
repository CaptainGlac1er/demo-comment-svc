package com.example.democomments.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table()
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn()
    private List<EmailRecipient> emailRecipientList;

    @Column()
    private String emailContents;

    @Column()
    private Timestamp posted = Timestamp.from(Instant.now());
}
