package com.agozuberk.cityserver;

import java.util.Random;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CityServerApplication implements ApplicationRunner {

	@Value("${server.city}")
	private String serverCity;

	@Value("${max.sleep.duration.between.logs.miliseconds}")
	private Integer maxSleepDuration;

	@Value("${min.sleep.duration.between.logs.miliseconds}")
	private Integer minSleepDuration;

	public static void main(String[] args) {
		SpringApplication.run(CityServerApplication.class, args);
	}

	public void run(ApplicationArguments applicationArguments) throws Exception {
		Logger logger = LogManager.getLogger(serverCity);
		String helloFromString = "Hello-from-".concat(serverCity);
		System.out.println(serverCity);
		while (true) {
			Thread.sleep(getRandomSleepDuration());
			logger.log(getRandomLogLevelValue(), helloFromString);
		}
	}

	public Level getRandomLogLevelValue() {
		Random rand = new Random();
		int randomLevelNumber = rand.nextInt(Level.values().length);
		return Level.values()[randomLevelNumber];
	}

	public Integer getRandomSleepDuration() {
		Random rand = new Random();
		return rand.nextInt(maxSleepDuration - minSleepDuration) + minSleepDuration;
	}

}
