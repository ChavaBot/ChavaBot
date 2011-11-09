package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.Channel;
import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;

public class SetModeratedEvent extends ChannelEvent<SetModeratedEvent> {
	private static final SetModeratedEvent instance = new SetModeratedEvent();
	private static final HandlerList<SetModeratedEvent> handlers = new HandlerList<SetModeratedEvent>();
	
	public static SetModeratedEvent getInstance(ChavaUser argUser, Channel channel) {
		instance.user = argUser;
		instance.channel = channel;
		return instance;
	}

	@Override
	public HandlerList<SetModeratedEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Set Moderated Event";
	}

}
