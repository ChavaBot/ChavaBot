package com.alta189.chavabot.event.botevents;

import org.pircbotx.User;

import com.alta189.chavabot.event.Cancellable;
import com.alta189.chavabot.event.Event;
import com.alta189.chavabot.event.HandlerList;

public class SendPrivateMessageEvent extends Event implements Cancellable {
	private static HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private User user;
	private String message;
	
	public SendPrivateMessageEvent(User user, String message) {
		this.user = user;
		this.message = message;
	}
	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getMessage() {
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
