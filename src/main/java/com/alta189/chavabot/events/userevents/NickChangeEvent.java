package com.alta189.chavabot.events.userevents;

import com.alta189.chavabot.events.HandlerList;

public class NickChangeEvent extends UserEvent<NickChangeEvent> {
	private static final NickChangeEvent instance = new NickChangeEvent();
	private static final HandlerList<NickChangeEvent> handlers = new HandlerList<NickChangeEvent>();
	
	public static NickChangeEvent getInstance() {
		return instance;
	}
	
	@Override
	public HandlerList<NickChangeEvent> getHandlers() {
		return handlers;
	}
	
	@Override
	protected String getEventName() {
		return "Connect Event";
	}
	
}
