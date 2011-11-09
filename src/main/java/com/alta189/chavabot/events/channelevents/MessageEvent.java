package com.alta189.chavabot.events.channelevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;

public class MessageEvent extends ChannelEvent<MessageEvent> {
	private static final MessageEvent instance = new MessageEvent();
	private static final HandlerList<MessageEvent> handlers = new HandlerList<MessageEvent>();
	private String message;
	private ChavaUser user;
	private String channel;
	
	public static MessageEvent getInstance(ChavaUser user, String channel, String message) {
		instance.user = user;
		instance.channel = channel;
		instance.message = message;
		return instance;
	}
	
	public ChavaUser getUser() {
		return user;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public HandlerList<MessageEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Message Event";
	}

}
