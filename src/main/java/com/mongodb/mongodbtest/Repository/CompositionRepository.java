package com.mongodb.mongodbtest.Repository;

import com.mongodb.mongodbtest.Model.Composition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositionRepository extends MongoRepository<Composition, String> {}
