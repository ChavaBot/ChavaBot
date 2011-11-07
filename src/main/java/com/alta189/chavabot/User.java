package com.alta189.chavabot;

public class User {
	private String nick;
	private final String hostname;
	private final String login;
	private String prefix;
	private final Channel channel;
	
	public User(String nick, String login, String hostname, Channel channel) {
		this.nick = nick;
		this.login = login;
		this.hostname = hostname;
		this.channel = channel;
	}
	
	public Channel getParentChannel() {
		return this.channel;
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
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public String getLogin() {
		return login;
	}
	
	
}
