package com.alta189.chavabot.event.botevents;

import org.pircbotx.Channel;

import com.alta189.chavabot.event.Cancellable;
import com.alta189.chavabot.event.Event;
import com.alta189.chavabot.event.HandlerList;

public class BotPartEvent extends Event implements Cancellable {
	private static HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private Channel channel;
	private String reason;

	public BotPartEvent(Channel channel, String reason) {
		this.channel = channel;
		this.reason = reason;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
