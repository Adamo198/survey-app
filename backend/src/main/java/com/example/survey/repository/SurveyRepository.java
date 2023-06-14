package com.example.survey.repository;

import com.example.survey.documents.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "survey", path = "survey")
public interface SurveyRepository extends JpaRepository<Survey, String> {
	Optional<Survey> findFirstByTitle(@Param("title") String title);

	@Override
	Optional<Survey> findById(String s);
}

