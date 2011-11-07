package com.alta189.chavabot.plugins;

public class IllegalPluginAccessException extends RuntimeException {

	private static final long serialVersionUID = -5160205301292389716L;

	public IllegalPluginAccessException() {
	}

	public IllegalPluginAccessException(String msg) {
		super(msg);
	}

}
