package com.alta189.chavabot;

import java.util.List;

public class ChavaUser {
	private final String nick;
	private final String hostname;
	private final String login;
	private final List<String> channels;
	
	public ChavaUser(String nick, String login, String hostname, List<String> channels) {
		this.nick = nick;
		this.login = login;
		this.hostname = hostname;
		this.channels = channels;
	}
	
	public List<String> getChannels() {
		return this.channels;
	}
	
	public boolean hasVoice(String channel) {
		return getPrefix(channel).equals("+");
	}
	
	public boolean isOp(String channel) {
		return getPrefix(channel).equals("@");
	}
	
	public String getNick() {
		return nick;
	}
	
	public String getPrefix(String channel) {
		for (String chan : channels) {
			if (chan.equalsIgnoreCase(channel)) {
				return "";
			} else if (chan.substring(1).equalsIgnoreCase(channel)) {
				return chan.substring(0,1);
			}
		}
		return "";
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
