package com.alta189.chavabot;

/**
 * @author: alta189
 */
public class Responses {
	private static Responses instance = new Responses();

	// About Chava Responses \\
	private final String build = "0";
	private final String built = "INDEV";
	private final String author = "alta189";
	private final String source = "http://github.com/Chava/Chava";
	private final String license = "LGPL";

	public static Responses getInstance() {
		return instance;
	}

	private Responses() {
	}
}
