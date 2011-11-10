package com.alta189.chavabot.events.ircevents;

import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class DisconnectEvent extends IrcEvent<DisconnectEvent> {
	private static final DisconnectEvent instance = new DisconnectEvent();
	private static final HandlerList<DisconnectEvent> handlers = new HandlerList<DisconnectEvent>();
	
	public static DisconnectEvent getInstance() {
		return instance;
	}
	
	public static void register(Listener<DisconnectEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}
	
	@Override
	public HandlerList<DisconnectEvent> getHandlers() {
		return handlers;
	}
	
	@Override
	protected String getEventName() {
		return "Disconnect Event";
	}
	
}
