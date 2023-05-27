package com.surveyapp.answerservice.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by adamo198 on 26/05/2023.
 * Github:adamo198
 */

@Document
@Data
public class Answer {

    @Id
    private String id;
    private String surveyId;
    private String name;
    private String lastName;
    private String email;
    private List<AnswerEntry> entries;
}
