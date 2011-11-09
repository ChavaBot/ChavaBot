package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;

public class PartEvent extends ChannelEvent<PartEvent> {
	private static final PartEvent instance = new PartEvent();
	private static final HandlerList<PartEvent> handlers = new HandlerList<PartEvent>();
	private String reason = null;
	private String channel;
	private ChavaUser user;
	
	public static PartEvent getInstance(ChavaUser argUser, String channel) {
		return PartEvent.getInstance(argUser, channel, null);
	}
	
	public static PartEvent getInstance(ChavaUser user, String channel, String reason) {
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

	@Override
	public HandlerList<PartEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Part Event";
	}

}
