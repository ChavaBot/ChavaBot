package com.alta189.chavabot;

public class ChavaUser {
	private final String nick;
	private final String hostname;
	private final String login;
	private final String[] channels;
	
	public ChavaUser(String nick, String login, String hostname, String[] channels) {
		this.nick = nick;
		this.login = login;
		this.hostname = hostname;
		this.channels = channels;
	}
	
	public String[] getChannels() {
		return this.channels;
	}
	
	public boolean hasVoice() {
		if (getPrefix() == null) return false;
		return getPrefix().equalsIgnoreCase("+");
	}
	
	public boolean isOp() {
		if (getPrefix() == null) return false;
		return getPrefix().equalsIgnoreCase("@");
	}
	
	public String getNick() {
		return nick;
	}
	
	public String getPrefix() {
		return null;
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public String getLogin() {
		return login;
	}
	
	public boolean isValid() {
		return (login != null || hostname != null);
	}
}
