package com.alta189.chavabot.events.ircevents;

import com.alta189.chavabot.events.HandlerList;

public class ConnectEvent extends IrcEvent<ConnectEvent> {
	private static final ConnectEvent instance = new ConnectEvent();
	private static final HandlerList<ConnectEvent> handlers = new HandlerList<ConnectEvent>();
	
	public static ConnectEvent getInstance() {
		return instance;
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
