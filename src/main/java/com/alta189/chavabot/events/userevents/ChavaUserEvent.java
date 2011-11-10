package com.alta189.chavabot.events.userevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

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
	
	public static void register(Listener<ChavaUserEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
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
