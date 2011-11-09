package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.Channel;
import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;

public class ChannelVoiceEvent extends ChannelEvent<ChannelVoiceEvent> {
	private static final ChannelVoiceEvent instance = new ChannelVoiceEvent();
	private static final HandlerList<ChannelVoiceEvent> handlers = new HandlerList<ChannelVoiceEvent>();
	private String hostmask;
	
	public static ChannelVoiceEvent getInstance(ChavaUser argUser, Channel channel, String hostmask) {
		instance.user = argUser;
		instance.channel = channel;
		instance.hostmask = hostmask;
		return instance;
	}

	public String getHostmask() {
		return hostmask;
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
