package com.agozuberk.kafkaproducer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class Sender {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String serverName, String payload) {
		System.out.println("sending payload=" + payload);
		// sends to "logs" topic
		kafkaTemplate.send("logs", payload);
		// send to server-spesific topic
		kafkaTemplate.send(serverName, payload);
	}

}
