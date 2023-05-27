package com.surveyapp.answerservice.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by adamo198 on 26/05/2023.
 * Github:adamo198
 */

@Data
public class Question {
    @Id
    private String id;
    private String name;
    private String type;
    private List<Option> options;

}
