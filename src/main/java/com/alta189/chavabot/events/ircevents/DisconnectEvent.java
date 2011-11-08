package com.alta189.chavabot.events.ircevents;

import com.alta189.chavabot.events.HandlerList;

public class DisconnectEvent extends IrcEvent<DisconnectEvent> {
	private static final DisconnectEvent instance = new DisconnectEvent();
	private static final HandlerList<DisconnectEvent> handlers = new HandlerList<DisconnectEvent>();
	
	public static DisconnectEvent getInstance() {
		return instance;
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
