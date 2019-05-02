package com.agozuberk.kafkaconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.agozuberk.kafkaconsumer.model.Log;
import com.agozuberk.kafkaconsumer.model.ServiceLogCounter;

@Component

public class KafkaConsumerService {
	@Autowired
	private SimpMessagingTemplate webSocket;

	// Consumes "logs" topic
	@KafkaListener(topics = "logs")
	public void consumeAllLogs(String content) {
		System.out.println("Consumed data : " + content);
		webSocket.convertAndSend("/topic/logs", new String(content));
	}

	// Consumes all topics but logs
	@KafkaListener(topics = { "Istanbul", "Moscow", "Tokyo", "Beijing", "London" })
	public void consumeServerSpesificLogs(String content, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
		System.out.println("Write to Db" + topic + "-" + content);
		if ("Istanbul".equals(topic)) {
			ServiceLogCounter.istanbulLogCounter++;
		} else if ("Moscow".equals(topic)) {
			ServiceLogCounter.moscowLogCounter++;
		} else if ("Tokyo".equals(topic)) {
			ServiceLogCounter.tokyoLogCounter++;
		} else if ("Beijing".equals(topic)) {
			ServiceLogCounter.beijingLogCounter++;
		} else if ("London".equals(topic)) {
			ServiceLogCounter.londonLogCounter++;
		}
		saveDocumentToDatabase(topic, content);
	}

	public void saveDocumentToDatabase(String serverName, String log) {
		final String mongodbServiceUri = "http://mongodb-server:8085/";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		Log newLogRecord = new Log();
		newLogRecord.setLog(log);
		newLogRecord.setServerName(serverName);
		restTemplate.postForEntity(mongodbServiceUri, newLogRecord, Log.class);
		System.out.println("Writed " + serverName + "-" + log);
	}

}
