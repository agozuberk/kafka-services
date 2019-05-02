package com.agozuberk.kafkaconsumer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.agozuberk.kafkaconsumer.model.ServiceLogCounter;

@SpringBootApplication
public class KafkaConsumerApplication implements ApplicationRunner {
	@Autowired
	private SimpMessagingTemplate webSocket;

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Timer timer = new Timer(30000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServiceLogCounter logCounter = new ServiceLogCounter();
				String serverLogCountValues = logCounter.toJson();
				logCounter.setCounterZero();
				System.out.println(serverLogCountValues);
				webSocket.convertAndSend("/topic/logcount", serverLogCountValues);
			}
		});
		timer.setRepeats(true);
		timer.start();

	}

}
