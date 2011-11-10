package com.alta189.chavabot.events.userevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class ActionEvent extends UserEvent<ActionEvent> {
	private static final ActionEvent instance = new ActionEvent();
	private static final HandlerList<ActionEvent> handlers = new HandlerList<ActionEvent>();
	private String action;
	private String target;
	
	public static ActionEvent getInstance(ChavaUser user, String target, String action) {
		instance.action = action;
		instance.user = user;
		instance.target = target;
		return instance;
	}
	
	public String getAction() {
		return action;
	}

	public String getTarget() {
		return target;
	}
	
	public static void register(Listener<ActionEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}

	@Override
	public HandlerList<ActionEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Action Event";
	}

}

