package com.surveyapp.answerservice.repository;

import com.surveyapp.answerservice.documents.Survey;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "survey", path = "survey")
public interface SurveyRepository extends CrudRepository<Survey, String> {
    Optional<Survey> findFirstByTitle(@Param("title") String title);
}
