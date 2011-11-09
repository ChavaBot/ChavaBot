package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.Channel;
import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;

public class SetChannelBanEvent extends ChannelEvent<SetChannelBanEvent> {
	private static final SetChannelBanEvent instance = new SetChannelBanEvent();
	private static final HandlerList<SetChannelBanEvent> handlers = new HandlerList<SetChannelBanEvent>();
	private String hostmask;
	
	public static SetChannelBanEvent getInstance(ChavaUser argUser, Channel channel, String hostmask) {
		instance.user = argUser;
		instance.channel = channel;
		instance.hostmask = hostmask;
		return instance;
	}

	public String getHostmask() {
		return hostmask;
	}

	@Override
	public HandlerList<SetChannelBanEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Set Moderated Event";
	}

}
