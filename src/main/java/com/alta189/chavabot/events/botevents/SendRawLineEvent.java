package com.alta189.chavabot.events.botevents;

import com.alta189.chavabot.events.Cancellable;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class SendRawLineEvent extends BotEvent<SendRawLineEvent> implements Cancellable {
	private static final SendRawLineEvent instance = new SendRawLineEvent();
	private static final HandlerList<SendRawLineEvent> handlers = new HandlerList<SendRawLineEvent>();
	private String raw;
	
	public static SendRawLineEvent getInstance(String raw) {
		instance.raw = raw;
		instance.setCancelled(false);
		return instance;
	}
	
	public void setRawLine(String raw) {
		this.raw = raw;
	}

	public String getRawLine() {
		return raw;
	}
	
	public static void register(Listener<SendRawLineEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}
	
	@Override
	public HandlerList<SendRawLineEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Send Message Event";
	}
	
	public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
    }
	
}

