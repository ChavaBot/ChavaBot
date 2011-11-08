package com.alta189.chavabot.events.userevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;

public class ChavaUserEvent extends UserEvent<ChavaUserEvent> {
	private static final ChavaUserEvent instance = new ChavaUserEvent();
	private static final HandlerList<ChavaUserEvent> handlers = new HandlerList<ChavaUserEvent>();
	private ChavaUser cu;
	
	public static ChavaUserEvent getInstance(ChavaUser cu) {
		return instance;
	}
	
	public ChavaUser getChavaUser() {
		return cu;
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
