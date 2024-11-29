package com.mongodb.mongodbtest.Repository;

import com.mongodb.mongodbtest.Model.Template;
import com.mongodb.mongodbtest.Model.TemplateMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends MongoRepository<TemplateMongoDB, String> {}
