package com.alta189.chavabot.events.botevents;

import com.alta189.chavabot.events.Cancellable;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class PartEvent extends BotEvent<PartEvent> implements Cancellable {
	private static final PartEvent instance = new PartEvent();
	private static final HandlerList<PartEvent> handlers = new HandlerList<PartEvent>();
	private String channel;
	private String reason;

	public static PartEvent getInstance(String channel, String reason) {
		instance.channel = channel;
		instance.reason = reason;
		instance.setCancelled(false);
		return instance;
	}
	
	public String getChannel() {
		return channel;
	}

	public String getReason() {
		return reason;
	}
	
	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public static void register(Listener<PartEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}

	@Override
	public HandlerList<PartEvent> getHandlers() {
		return handlers;
	}
	
	@Override
	protected String getEventName() {
		return "Change Nick Event";
	}
	
	public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
    }
	
}

