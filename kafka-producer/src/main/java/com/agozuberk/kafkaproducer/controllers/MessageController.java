package com.agozuberk.kafkaproducer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agozuberk.kafkaproducer.model.MessageRequest;
import com.agozuberk.kafkaproducer.services.Sender;

@RestController
public class MessageController {
	@Autowired
	private Sender sender;

	@RequestMapping(value = "/send", method = RequestMethod.PUT)
	public void sendToKafka(@RequestBody MessageRequest messageRequest) {
		sender.send(messageRequest.getServerName(), messageRequest.getMessage());
	}

}
