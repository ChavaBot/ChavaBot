package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class ChannelVoiceEvent extends ChannelEvent<ChannelVoiceEvent> {
	private static final ChannelVoiceEvent instance = new ChannelVoiceEvent();
	private static final HandlerList<ChannelVoiceEvent> handlers = new HandlerList<ChannelVoiceEvent>();
	private String recipient;
	private ChavaUser user;
	private String channel;
	
	
	public static ChannelVoiceEvent getInstance(ChavaUser argUser, String channel, String recipient) {
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
	
	public static void register(Listener<ChannelVoiceEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}

	@Override
	public HandlerList<ChannelVoiceEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Channel Voice Event";
	}

}
