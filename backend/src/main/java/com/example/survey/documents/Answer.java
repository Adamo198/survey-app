package com.example.survey.documents;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Answer {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	@ManyToOne(fetch = FetchType.EAGER)
    private Survey survey;
    private String surveyId;
    private String name;
    private String lastName;
    private String email;
	@OneToMany(fetch = FetchType.EAGER,
			orphanRemoval = true,
			cascade = CascadeType.ALL)
    private List<AnswerEntry> entries;
}
