package com.alta189.chavabot.events.userevents;

import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

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
	
	public static void register(Listener<NickChangeEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
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
