package com.example.democomments.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "email_recipient")
public class EmailRecipient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column()
    private String emailAddress;

    @JoinColumn()
    @ManyToOne()
    private Email email;

}
