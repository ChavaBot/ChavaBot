package com.alta189.chavabot.events.userevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;

public class ChavaUserEvent extends UserEvent<ChavaUserEvent> {
	private static final ChavaUserEvent instance = new ChavaUserEvent();
	private static final HandlerList<ChavaUserEvent> handlers = new HandlerList<ChavaUserEvent>();
	
	public static ChavaUserEvent getInstance(ChavaUser user) {
		instance.user = user;
		return instance;
	}
	
	public ChavaUser getChavaUser() {
		return user;
	}
	
	@Override
	public HandlerList<ChavaUserEvent> getHandlers() {
		return handlers;
	}
	
	@Override
	protected String getEventName() {
		return "Chava User Event";
	}
	
}
