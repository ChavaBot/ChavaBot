package com.alta189.chavabot.plugins;

public class InvalidDescriptionException extends Exception {
	private static final long serialVersionUID = 1108896117710718588L;
	private final Throwable cause;
	private final String message;

	public InvalidDescriptionException(Throwable throwable) {
		this(throwable, "Invalid plugin.yml");
	}

	public InvalidDescriptionException(final String message) {
		this(null, message);
	}

	public InvalidDescriptionException(final Throwable throwable,
			final String message) {
		this.cause = null;
		this.message = message;
	}

	public InvalidDescriptionException() {
		this(null, "Invalid plugin.yml");
	}

	@Override
	public Throwable getCause() {
		return cause;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
