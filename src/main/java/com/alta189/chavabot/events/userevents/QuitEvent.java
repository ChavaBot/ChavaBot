package com.alta189.chavabot.events.userevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;

public class QuitEvent extends UserEvent<QuitEvent> {
	private static final QuitEvent instance = new QuitEvent();
	private static final HandlerList<QuitEvent> handlers = new HandlerList<QuitEvent>();
	private String reason;
	
	public static QuitEvent getInstance(ChavaUser user, String reason) {
		instance.user = user;
		instance.reason = reason;
		return instance;
	}
	
	public String getReason() {
		return reason;
	}
	
	public ChavaUser getUser() {
		return user;
	}

	@Override
	public HandlerList<QuitEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "";
	}

}
