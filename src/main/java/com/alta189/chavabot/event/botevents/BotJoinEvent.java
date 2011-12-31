package com.alta189.chavabot.event.botevents;

import com.alta189.chavabot.event.Cancellable;
import com.alta189.chavabot.event.Event;
import com.alta189.chavabot.event.HandlerList;

public class BotJoinEvent extends Event implements Cancellable {
	private static HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private String channel;
	
	public BotJoinEvent(String channel) {
		this.channel = channel;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
