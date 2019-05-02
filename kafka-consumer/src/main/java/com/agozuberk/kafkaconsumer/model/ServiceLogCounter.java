package com.agozuberk.kafkaconsumer.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServiceLogCounter {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static int istanbulLogCounter;
	public static int moscowLogCounter;
	public static int tokyoLogCounter;
	public static int beijingLogCounter;
	public static int londonLogCounter;

	private int istanbul;
	private int moscow;
	private int tokyo;
	private int beijing;
	private int london;

	public ServiceLogCounter() {
		this.istanbul = istanbulLogCounter;
		this.moscow = moscowLogCounter;
		this.tokyo = tokyoLogCounter;
		this.beijing = beijingLogCounter;
		this.london = londonLogCounter;
	}

	public String toJson() {
		return GSON.toJson(this, ServiceLogCounter.class);
	}

	public void setCounterZero() {
		istanbulLogCounter = 0;
		moscowLogCounter = 0;
		tokyoLogCounter = 0;
		beijingLogCounter = 0;
		londonLogCounter = 0;
	}

}
