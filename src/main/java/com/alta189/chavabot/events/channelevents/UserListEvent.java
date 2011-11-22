package com.alta189.chavabot.events.channelevents;

import org.jibble.pircbot.User;

import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class UserListEvent extends ChannelEvent<UserListEvent> {
	private static final UserListEvent instance = new UserListEvent();
	private static final HandlerList<UserListEvent> handlers = new HandlerList<UserListEvent>();
	private String channel;
	private User[] users;
	
	public static UserListEvent getInstance(User[] users, String channel) {
		instance.users = users;
		instance.channel = channel;
		return instance;
	}
	
	public User[] getUsers() {
		return users;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public static void register(Listener<UserListEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}

	@Override
	public HandlerList<UserListEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Join Event";
	}

}
