package com.alta189.chavabot.event.botevents;

import com.alta189.chavabot.event.Event;
import com.alta189.chavabot.event.HandlerList;

public class SendRawLineEvent extends Event {
	private static HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private String rawline;
	private boolean immediately;
	
	public SendRawLineEvent(String rawline, boolean immediately) {
		this.rawline = rawline;
	}
	
	public String getRawLine() {
		return rawline;
	}

	public void setRawLine(String rawline) {
		this.rawline = rawline;
	}
	
	public boolean isImmediately() {
		return immediately;
	}
	
	public void setImmediately(boolean immediately) {
		this.immediately = immediately;
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
