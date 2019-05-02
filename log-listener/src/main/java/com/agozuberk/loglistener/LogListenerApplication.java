package com.agozuberk.loglistener;

import java.io.File;
import java.net.InetAddress;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agozuberk.loglistener.configurations.CustomRunnable;
import com.agozuberk.loglistener.services.LogTailer;

@SpringBootApplication
public class LogListenerApplication implements ApplicationRunner {

	@Value("${service.list.to.listen}")
	private String serviceListToListen;

	public static void main(String[] args) {
		SpringApplication.run(LogListenerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		// Wait until kafka producer service become available
		Boolean isKafkaProducerServiceUp = Boolean.FALSE;
		while (!isKafkaProducerServiceUp) {
			if (InetAddress.getByName("kafka-producer").isReachable(3000)) {
				isKafkaProducerServiceUp = Boolean.TRUE;
				System.out.println("KAFKA SERVICE IS UP:" + isKafkaProducerServiceUp);
			}

			Thread.sleep(5000);
		}

		String[] serviceList = serviceListToListen.split(",");
		for (String service : serviceList) {
			String serviceLogFilePath = "./logs/".concat(service).concat("/serverlog");
			System.out.println(serviceLogFilePath);
			TailerListener listener = new LogTailer();
			File logFileToListen = new File(serviceLogFilePath);
			Tailer tailer = new Tailer(logFileToListen, listener, 100);
			runListenerInNewThread(tailer, service);
		}
	}

	public void runListenerInNewThread(Tailer tailer, String serverName) {
		Runnable r = new CustomRunnable(tailer);
		Thread thread = new Thread(r);
		thread.setName(serverName);
		thread.start();
	}
}
