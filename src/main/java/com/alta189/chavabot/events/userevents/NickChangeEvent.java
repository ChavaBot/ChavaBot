package com.alta189.chavabot.events.userevents;

import com.alta189.chavabot.events.HandlerList;

public class NickChangeEvent extends UserEvent<NickChangeEvent> {
	private static final NickChangeEvent instance = new NickChangeEvent();
	private static final HandlerList<NickChangeEvent> handlers = new HandlerList<NickChangeEvent>();
	private String oldNick;
	private String newNick;
	
	public static NickChangeEvent getInstance(String oldNick, String newNick) {
		instance.oldNick = oldNick;
		instance.newNick = newNick;
		return instance;
	}
	
	public String getOldNick() {
		return oldNick;
	}

	public String getNewNick() {
		return newNick;
	}
	
	@Override
	public HandlerList<NickChangeEvent> getHandlers() {
		return handlers;
	}
	
	@Override
	protected String getEventName() {
		return "Nick Change Event";
	}
	
}
