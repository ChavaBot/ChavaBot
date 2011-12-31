package com.alta189.chavabot.event.botevents;

import org.pircbotx.Channel;

import com.alta189.chavabot.event.Cancellable;
import com.alta189.chavabot.event.Event;
import com.alta189.chavabot.event.HandlerList;

public class SendActionEvent extends Event implements Cancellable {
	private static HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private Channel channel;
	private String message;
	
	public SendActionEvent(Channel channel, String message) {
		this.channel = channel;
		this.message = message;
	}
	

	public Channel getChannel() {
		return channel;
	}


	public void setChannel(Channel channel) {
		this.channel = channel;
	}


	public String getAction() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
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
