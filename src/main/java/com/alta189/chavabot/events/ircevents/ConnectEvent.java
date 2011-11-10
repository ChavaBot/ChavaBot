package com.alta189.chavabot.events.ircevents;

import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class ConnectEvent extends IrcEvent<ConnectEvent> {
	private static final ConnectEvent instance = new ConnectEvent();
	private static final HandlerList<ConnectEvent> handlers = new HandlerList<ConnectEvent>();
	
	public static ConnectEvent getInstance() {
		return instance;
	}
	
	public static void register(Listener<ConnectEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}
	
	@Override
	public HandlerList<ConnectEvent> getHandlers() {
		return handlers;
	}
	
	@Override
	protected String getEventName() {
		return "Connect Event";
	}
	
}
