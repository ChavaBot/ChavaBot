package com.alta189.chavabot.plugins;

public class InvalidPluginException extends Exception {
	
	private static final long serialVersionUID = 6697052704920584047L;
	private final Throwable cause;

	public InvalidPluginException(Throwable throwable) {
		this.cause = throwable;
	}

	public InvalidPluginException() {
		this.cause = null;
	}

	public Throwable getCause() {
		return this.cause;
	}

}
