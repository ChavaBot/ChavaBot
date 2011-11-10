package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class ChannelJoinEvent extends ChannelEvent<ChannelJoinEvent> {
	private static final ChannelJoinEvent instance = new ChannelJoinEvent();
	private static final HandlerList<ChannelJoinEvent> handlers = new HandlerList<ChannelJoinEvent>();
	private ChavaUser user;
	private String channel;
	
	public static ChannelJoinEvent getInstance(ChavaUser argUser, String channel) {
		instance.user = argUser;
		instance.channel = channel;
		return instance;
	}
	
	public ChavaUser getUser() {
		return user;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public static void register(Listener<ChannelJoinEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}

	@Override
	public HandlerList<ChannelJoinEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Join Event";
	}

}
