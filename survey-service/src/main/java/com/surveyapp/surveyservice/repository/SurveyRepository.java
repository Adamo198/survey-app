package com.surveyapp.surveyservice.repository;

import com.surveyapp.surveyservice.documents.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "survey", path = "survey")
public interface SurveyRepository extends MongoRepository<Survey, String> {
    Optional<Survey> findFirstByTitle(@Param("title") String title);
}
