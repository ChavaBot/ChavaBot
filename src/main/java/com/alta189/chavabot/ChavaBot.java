package com.alta189.chavabot;

/**
 * @author: alta189
 */
public class ChavaBot implements User {
	private String fullName;
	private String nick;

	public void setNick() {

	}

	public String getNick() {
		return nick;
	}

	public String getPrefix() {
		return null;
	}

	public boolean isOp(String channel) {
		return false;
	}

	public boolean hasOp(String channel) {
		return false;
	}

	public String getHost() {
		return null;
	}

	public String getFullName() {
		return fullName;
	}
}
