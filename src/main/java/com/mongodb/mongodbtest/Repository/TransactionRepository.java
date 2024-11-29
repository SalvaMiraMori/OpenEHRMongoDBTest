package com.mongodb.mongodbtest.Repository;

import com.mongodb.mongodbtest.Model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository <Transaction, String>{}
