package com.example.democomments.repositories;

import com.example.democomments.entities.EmailRecipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRecipientRepository extends JpaRepository<EmailRecipient, Long> {

}
