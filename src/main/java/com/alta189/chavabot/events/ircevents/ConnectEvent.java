package com.alta189.chavabot.events.ircevents;

import com.alta189.chavabot.events.HandlerList;

public class ConnectEvent extends IrcEvent<ConnectEvent> {
	private static final ConnectEvent instance = new ConnectEvent();
	private static final HandlerList<ConnectEvent> handlers = new HandlerList<ConnectEvent>();
	
	@Override
	public HandlerList<ConnectEvent> getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getEventName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
