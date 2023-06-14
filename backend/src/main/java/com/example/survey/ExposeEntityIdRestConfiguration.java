package com.example.survey;

import com.example.survey.documents.Answer;
import com.example.survey.documents.Survey;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class ExposeEntityIdRestConfiguration implements RepositoryRestConfigurer {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
		config.exposeIdsFor(Survey.class);
		config.exposeIdsFor(Answer.class);
	}
}
