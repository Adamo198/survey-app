package com.example.survey.documents;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AnswerEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Question question;
    private String answer;
}
