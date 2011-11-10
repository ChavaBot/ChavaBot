package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class SetChannelBanEvent extends ChannelEvent<SetChannelBanEvent> {
	private static final SetChannelBanEvent instance = new SetChannelBanEvent();
	private static final HandlerList<SetChannelBanEvent> handlers = new HandlerList<SetChannelBanEvent>();
	private String hostmask;
	private ChavaUser user;
	private String channel;
	
	public static SetChannelBanEvent getInstance(ChavaUser argUser, String channel, String hostmask) {
		instance.user = argUser;
		instance.channel = channel;
		instance.hostmask = hostmask;
		return instance;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public ChavaUser getUser() {
		return user;
	}
	
	public String getHostmask() {
		return hostmask;
	}
	
	public static void register(Listener<SetChannelBanEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}

	@Override
	public HandlerList<SetChannelBanEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Set Channel Ban Event";
	}

}
