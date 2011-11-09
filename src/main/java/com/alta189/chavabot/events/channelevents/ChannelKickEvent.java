package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;

public class ChannelKickEvent extends ChannelEvent<ChannelKickEvent> {
	private static final ChannelKickEvent instance = new ChannelKickEvent();
	private static final HandlerList<ChannelKickEvent> handlers = new HandlerList<ChannelKickEvent>();
	private String recipient;
	private String reason;
	private String channel;
	private ChavaUser user;
	
	public static ChannelKickEvent getInstance(ChavaUser argUser, String channel, String recipient, String reason) {
		instance.user = argUser;
		instance.channel = channel;
		instance.recipient = recipient;
		instance.reason = reason;
		return instance;
	}
	
	public ChavaUser getUser() {
		return user;
	}
	public String getChannel() {
		return channel;
	}
	
	public String getReason() {
		return reason;
	}

	public String getRecipient() {
		return recipient;
	}

	@Override
	public HandlerList<ChannelKickEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Channel Kick Event";
	}

}
