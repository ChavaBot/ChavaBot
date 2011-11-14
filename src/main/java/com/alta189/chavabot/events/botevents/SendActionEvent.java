package com.alta189.chavabot.events.botevents;

import com.alta189.chavabot.events.Cancellable;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class SendActionEvent extends BotEvent<SendActionEvent> implements Cancellable {
	private static final SendActionEvent instance = new SendActionEvent();
	private static final HandlerList<SendActionEvent> handlers = new HandlerList<SendActionEvent>();
	private String action;
	private String target;
	
	public static SendActionEvent getInstance(String action, String target) {
		instance.action = action;
		instance.target = target;
		instance.setCancelled(false);
		return instance;
	}
	
	public void setAction(String action) {
		this.action = action;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getAction() {
		return action;
	}

	public String getTarget() {
		return target;
	}
	
	public static void register(Listener<SendActionEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}
	
	@Override
	public HandlerList<SendActionEvent> getHandlers() {
		return handlers;
	}
	
	@Override
	protected String getEventName() {
		return "Send Action Event";
	}
	
	public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
    }
	
}

