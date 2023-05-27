package com.surveyapp.answerservice.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by adamo198 on 26/05/2023.
 * Github:adamo198
 */

@Data
public class AnswerEntry {
    @Id
    private String id;
    private Question question;
    private String answer;
}
