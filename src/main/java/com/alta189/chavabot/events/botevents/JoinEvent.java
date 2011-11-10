package com.alta189.chavabot.events.botevents;

import com.alta189.chavabot.events.Cancellable;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class JoinEvent extends BotEvent<JoinEvent> implements Cancellable {
	private static final JoinEvent instance = new JoinEvent();
	private static final HandlerList<JoinEvent> handlers = new HandlerList<JoinEvent>();
	private String channel;

	public static JoinEvent getInstance(String channel) {
		instance.channel = channel;
		instance.setCancelled(false);
		return instance;
	}
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public static void register(Listener<JoinEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}

	@Override
	public HandlerList<JoinEvent> getHandlers() {
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

