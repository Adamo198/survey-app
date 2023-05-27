package com.surveyapp.surveyservice.config;

import com.surveyapp.surveyservice.documents.Survey;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class CustomizedRestMvcConfiguration implements RepositoryRestConfigurer {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    config.exposeIdsFor(Survey.class);
  }
}
