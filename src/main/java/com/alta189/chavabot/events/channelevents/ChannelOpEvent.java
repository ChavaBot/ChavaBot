package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class ChannelOpEvent extends ChannelEvent<ChannelOpEvent> {
	private static final ChannelOpEvent instance = new ChannelOpEvent();
	private static final HandlerList<ChannelOpEvent> handlers = new HandlerList<ChannelOpEvent>();
	private String recipient;
	private ChavaUser user;
	private String channel;
	
	
	public static ChannelOpEvent getInstance(ChavaUser argUser, String channel, String recipient) {
		instance.user = argUser;
		instance.channel = channel;
		instance.recipient = recipient;
		return instance;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public ChavaUser getUser() {
		return user;
	}

	public String getRecipient() {
		return recipient;
	}
	
	public static void register(Listener<ChannelOpEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}

	@Override
	public HandlerList<ChannelOpEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Channel Op Event";
	}

}