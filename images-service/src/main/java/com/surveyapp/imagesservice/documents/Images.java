package com.surveyapp.imagesservice.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by adamo198 on 28/05/2023.
 * Github:adamo198
 */
@Document
@Data
public class Images {

    @Id
    private String id;
    private String catImageUrl;

}
