package com.surveyapp.surveyservice.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by adamo198 on 26/05/2023.
 * Github:adamo198
 */
@Document
@Data
public class Survey {

    @Id
    private String id;
    private String title;
    private String email;
    private List<Question> questions;

}
