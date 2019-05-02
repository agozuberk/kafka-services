package com.agozuberk.loglistener.configurations;

import org.apache.commons.io.input.Tailer;

public class CustomRunnable implements Runnable {
	private Tailer tailer;

	public CustomRunnable(Tailer tailer) {
		this.tailer = tailer;
	}

	public void run() {
		tailer.run();
	}

	public Tailer getTailer() {
		return tailer;
	}

	public void setTailer(Tailer tailer) {
		this.tailer = tailer;
	}

}
