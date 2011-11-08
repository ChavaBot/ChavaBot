package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.Channel;
import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;

public class PartEvent extends ChannelEvent<PartEvent> {
	private static final PartEvent instance = new PartEvent();
	private static final HandlerList<PartEvent> handlers = new HandlerList<PartEvent>();
	private String reason = null;
	
	public static PartEvent getInstance(ChavaUser argUser, Channel channel) {
		return PartEvent.getInstance(argUser, channel, null);
	}
	
	public static PartEvent getInstance(ChavaUser argUser, Channel channel, String reason) {
		instance.user = argUser;
		instance.channel = channel;
		return instance;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	@Override
	public HandlerList<PartEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "";
	}

}
