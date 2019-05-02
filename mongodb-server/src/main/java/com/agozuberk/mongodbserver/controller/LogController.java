package com.agozuberk.mongodbserver.controller;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agozuberk.mongodbserver.model.Log;
import com.agozuberk.mongodbserver.repository.LogRepository;

@RestController
public class LogController {

	@Autowired
	private LogRepository repository;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Log createLog(@Valid @RequestBody Log log) {
		log.set_id(ObjectId.get());
		repository.save(log);
		return log;
	}

}
