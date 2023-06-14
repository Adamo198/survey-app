package com.example.survey.repository;

import com.example.survey.documents.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "answer", path = "answer")
public interface AnswerRepository  extends JpaRepository<Answer, String> {
	@Override
	Optional<Answer> findById(String s);

	List<Answer> findAllBySurveyTitle(String title);

	List<Answer> findAllBySurveyId(String surveyId);

	@Override
	List<Answer> findAllById(Iterable<String> strings);
}
