package com.alta189.chavabot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jibble.pircbot.User;

public class Channel {
	private Map<String, User> users = new HashMap<String, User>();
	private String topic;
	private final String channel;
	
	public Channel(String channel) {
		this.channel = channel;
	}
	
	/**
	 * Returns an ArrayList of Users in the channel
	 * @return users
	 */
	public ArrayList<User> getUsers() {
		return new ArrayList<User>(users.values());
	}
	
	/**
	 *  Gets the User object of that nick
	 * @param nick 
	 * @return
	 */
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
	
	/**
	 * Returns the topic of that channel
	 * @return topic 
	 */
	public String getTopic() {
		return topic;
	}

	protected void setTopic(String topic) {
		this.topic = topic;
	}
	
	/**
	 * Returns the channel name
	 * @return name
	 */
	public String getName() {
		return channel;
	}

}
