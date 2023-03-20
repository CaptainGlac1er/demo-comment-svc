package com.example.democomments.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class EmailDto {
    private Long id;
    private List<String> emailRecipients;
    private String emailContents;
    private Timestamp timestamp;
}
