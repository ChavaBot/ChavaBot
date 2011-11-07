package com.alta189.chavabot.plugins;

public class UnknownSoftDependencyException extends UnknownDependencyException {

	private static final long serialVersionUID = -1288053092685396071L;

	public UnknownSoftDependencyException(Throwable throwable) {
		this(throwable, "Unknown soft dependency");
	}

	public UnknownSoftDependencyException(final String message) {
		this(null, message);
	}

	public UnknownSoftDependencyException(final Throwable throwable, final String message) {
		super(throwable, message);
	}

	public UnknownSoftDependencyException() {
		this(null, "Unknown dependency");
	}

}
