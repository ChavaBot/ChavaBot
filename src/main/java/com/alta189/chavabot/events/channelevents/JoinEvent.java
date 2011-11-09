package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;

public class JoinEvent extends ChannelEvent<JoinEvent> {
	private static final JoinEvent instance = new JoinEvent();
	private static final HandlerList<JoinEvent> handlers = new HandlerList<JoinEvent>();
	private ChavaUser user;
	private String channel;
	
	public static JoinEvent getInstance(ChavaUser argUser, String channel) {
		instance.user = argUser;
		instance.channel = channel;
		return instance;
	}
	
	public ChavaUser getUser() {
		return user;
	}
	
	public String getChannel() {
		return channel;
	}

	@Override
	public HandlerList<JoinEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Join Event";
	}

}
