package com.surveyapp.answerservice.repository;

import com.surveyapp.answerservice.documents.Answer;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by adamo198 on 20/05/2023.
 * Github:adamo198
 */
@RepositoryRestResource(collectionResourceRel = "answer", path = "answer")
public interface AnswerRepository extends CrudRepository<Answer, String> {
//    @Query(value = "{'survey.title': ?0}")
//    @Query(value = "SELECT * FROM SURVEY u WHERE u.title = 1", nativeQuery = true)
    List<Answer> findAllBySurveyTitle(String title);

//    @Query(value = "{ 'surveyId': ?0 }")
    List<Answer> findAllBySurveyId(@Param("surveyId") String surveyId);
}
