package com.alta189.chavabot.events.botevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class InvitedEvent extends BotEvent<InvitedEvent> {
	private static final InvitedEvent instance = new InvitedEvent();
	private static final HandlerList<InvitedEvent> handlers = new HandlerList<InvitedEvent>();
	private String channel;
	private ChavaUser user;
	
	public static InvitedEvent getInstance(String channel, ChavaUser sender) {
		instance.channel = channel;
		instance.user = sender;
		return instance;
	}

	public String getChannel() {
		return channel;
	}

	public ChavaUser getSender() {
		return user;
	}
	
	public static void register(Listener<InvitedEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}
	
	@Override
	public HandlerList<InvitedEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Invited Event";
	}
	
	public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
    }
	
}
