package com.alta189.chavabot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Channel {
	private Map<String, User> users = new HashMap<String, User>();
	private String motd;
	
	public ArrayList<User> getUsers() {
		return new ArrayList<User>(users.values());
	}
	
	public User getUser(String nick) {
		return users.get(nick);
	}
	
	public void addUser(User user) {
		users.put(user.getNick(), user);
	}

	public String getMotd() {
		return motd;
	}

	public void setMotd(String motd) {
		this.motd = motd;
	}

}
