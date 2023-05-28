package com.surveyapp.imagesservice.repository;

import com.surveyapp.imagesservice.documents.Images;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "images", path = "images")
public interface ImagesRepository extends MongoRepository<Images, String> {

}
