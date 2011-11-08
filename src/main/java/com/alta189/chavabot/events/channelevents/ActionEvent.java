package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.Channel;
import com.alta189.chavabot.User;
import com.alta189.chavabot.events.HandlerList;

public class ActionEvent extends ChannelEvent<ActionEvent> {
	private static final ActionEvent instance = new ActionEvent();
	private static final HandlerList<ActionEvent> handlers = new HandlerList<ActionEvent>();
	private String action;
	
	public static void getInstance(User argUser, Channel channel, String action) {
		instance.user = argUser;
		instance.channel = channel;
		instance.action = action;
	}
	
	public String getAction() {
		return action;
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

