package com.alta189.chavabot.events.botevents;

import com.alta189.chavabot.events.HandlerList;

public class ServerPingEvent extends BotEvent<ServerPingEvent> {
	private static final ServerPingEvent instance = new ServerPingEvent();
	private static final HandlerList<ServerPingEvent> handlers = new HandlerList<ServerPingEvent>();
	private String response;
	
	public static ServerPingEvent getInstance(String response) {
		instance.response = response;
		return instance;
	}

	public String getTarget() {
		return response;
	}
	
	@Override
	public HandlerList<ServerPingEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Send Message Event";
	}
	
}
