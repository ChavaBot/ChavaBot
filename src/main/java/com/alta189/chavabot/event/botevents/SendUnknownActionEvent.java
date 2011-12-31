package com.alta189.chavabot.event.botevents;

import com.alta189.chavabot.event.Cancellable;
import com.alta189.chavabot.event.Event;
import com.alta189.chavabot.event.HandlerList;

public class SendUnknownActionEvent extends Event implements Cancellable {
	private static HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private String target;
	private String message;
	
	public SendUnknownActionEvent(String target, String message) {
		this.target = target;
		this.message = message;
	}
	

	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
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
