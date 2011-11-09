package com.alta189.chavabot.events.botevents;

import com.alta189.chavabot.events.Cancellable;
import com.alta189.chavabot.events.HandlerList;

public class ChangeNickEvent extends BotEvent<ChangeNickEvent> implements Cancellable {
	private static final ChangeNickEvent instance = new ChangeNickEvent();
	private static final HandlerList<ChangeNickEvent> handlers = new HandlerList<ChangeNickEvent>();
	private String oldNick;
	private String newNick;

	public static ChangeNickEvent getInstance(String oldNick, String newNick) {
		instance.oldNick = oldNick;
		instance.newNick = newNick;
		instance.setCancelled(false);
		return instance;
	}
	
	public String getOldNick() {
		return oldNick;
	}

	public String getNewNick() {
		return newNick;
	}
	
	public void setOldNick(String oldNick) {
		this.oldNick = oldNick;
	}

	public void setNewNick(String newNick) {
		this.newNick = newNick;
	}

	@Override
	public HandlerList<ChangeNickEvent> getHandlers() {
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

