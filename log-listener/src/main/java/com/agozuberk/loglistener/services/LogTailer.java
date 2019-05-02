package com.agozuberk.loglistener.services;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.json.Json;

import org.apache.commons.io.input.TailerListenerAdapter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class LogTailer extends TailerListenerAdapter {

	public void handle(String log) {
		System.out.println("Log listener: new log:" + log);
		String serverName = Thread.currentThread().getName();
		System.out.println("Log listener: server name:" + serverName);
		try {
			callKafkaSenderService(serverName, log);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void callKafkaSenderService(String serverName, String log) throws UnknownHostException, IOException {
		System.out.println("Log listener: send to kafka log:" + serverName + "-" + log);
		final String kafkaProducerServiceUri = "http://kafka-producer:7070/send";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(generateJson(serverName, log), headers);
		restTemplate.put(kafkaProducerServiceUri, entity);
		System.out.println("Sent to kafka" + log);

	}

	private String generateJson(String serverName, String log) {
		return Json.createObjectBuilder().add("serverName", serverName).add("message", log).build().toString();

	}

}
