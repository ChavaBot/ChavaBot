package com.alta189.chavabot.plugins;

public class UnknownDependencyException extends Exception {
	private static final long serialVersionUID = 1234821378686747098L;
	private final Throwable cause;
	private final String message;

	public UnknownDependencyException(Throwable throwable) {
		this(throwable, "Unknown dependency");
	}

	public UnknownDependencyException(String message) {
		this(null, message);
	}

	public UnknownDependencyException(Throwable throwable, String message) {
		this.cause = null;
		this.message = message;
	}

	public UnknownDependencyException() {
		this(null, "Unknown dependency");
	}

	public Throwable getCause() {
		return this.cause;
	}

	public String getMessage() {
		return this.message;
	}

}
