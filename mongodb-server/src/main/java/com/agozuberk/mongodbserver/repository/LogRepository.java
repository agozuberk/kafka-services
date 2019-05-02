package com.agozuberk.mongodbserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.agozuberk.mongodbserver.model.Log;

public interface LogRepository extends MongoRepository<Log, String> {

}
