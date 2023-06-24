package com.example.survey.repository;

import com.example.survey.documents.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RepositoryRestResource(collectionResourceRel = "survey", path = "survey")
public interface SurveyRepository extends JpaRepository<Survey, String> {
	Optional<Survey> findFirstByTitle(@Param("title") String title);

	@Override
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Survey save(Survey entity);

//	@Override
//	void deleteById(String s);

//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	void deleteSurveyById(Long id);

	void deleteById(Long id);

	@Override
	void deleteById(String s);

	@Override
	Optional<Survey> findById(String s);

//	@Override
	List<Survey> findByEmail(@Param("email") String email);

	List<Survey> findByOwner_Username(@Param("username") String username);
}

