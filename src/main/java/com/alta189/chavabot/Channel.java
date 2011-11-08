package com.alta189.chavabot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jibble.pircbot.User;

public class Channel {
	private Map<String, User> users = new HashMap<String, User>();
	private String motd;
	private final String channel;
	
	public Channel(String channel) {
		this.channel = channel;
	}
	
	public ArrayList<User> getUsers() {
		return new ArrayList<User>(users.values());
	}
	
	public User getUser(String nick) {
		return users.get(nick);
	}
	
	protected void addUsers(User[] users) {
		for (User user : users) {
			this.users.put(user.getNick(), user);
		}
	}
	
	protected void addUsers(User user) {
		users.put(user.getNick(), user);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof String) {
			return ((String)obj).equalsIgnoreCase(channel);
		} else if (obj instanceof Channel){
			return ((Channel)obj).getName().equalsIgnoreCase(channel);
		}
		return false;
	}

	@Override
	public String toString() {
		return channel;
	}

	public String getMotd() {
		return motd;
	}

	protected void setMotd(String motd) {
		this.motd = motd;
	}
	
	public String getName() {
		return channel;
	}

}
