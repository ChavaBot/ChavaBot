package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class ChannelPartEvent extends ChannelEvent<ChannelPartEvent> {
	private static final ChannelPartEvent instance = new ChannelPartEvent();
	private static final HandlerList<ChannelPartEvent> handlers = new HandlerList<ChannelPartEvent>();
	private String reason = null;
	private String channel;
	private ChavaUser user;
	
	public static ChannelPartEvent getInstance(ChavaUser argUser, String channel) {
		return ChannelPartEvent.getInstance(argUser, channel, null);
	}
	
	public static ChannelPartEvent getInstance(ChavaUser user, String channel, String reason) {
		instance.user = user;
		instance.channel = channel;
		return instance;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public ChavaUser getUser() {
		return user;
	}
	
	public String getReason() {
		return reason;
	}
	
	public static void register(Listener<ChannelPartEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}

	@Override
	public HandlerList<ChannelPartEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Part Event";
	}

}
