package com.alta189.chavabot.events.userevents;

import com.alta189.chavabot.ChavaUser;
import com.alta189.chavabot.events.HandlerList;
import com.alta189.chavabot.events.Listener;
import com.alta189.chavabot.events.Order;
import com.alta189.chavabot.plugins.Plugin;

public class NoticeEvent extends UserEvent<NoticeEvent> {
	private static final NoticeEvent instance = new NoticeEvent();
	private static final HandlerList<NoticeEvent> handlers = new HandlerList<NoticeEvent>();
	private String notice;
	private String target;
	
	public static NoticeEvent getInstance(ChavaUser user, String target, String notice) {
		instance.notice = notice;
		instance.user = user;
		instance.target = target;
		return instance;
	}
	
	public ChavaUser getSender() {
		return user;
	}
	
	public String getNotice() {
		return notice;
	}

	public String getTarget() {
		return target;
	}
	
	public static void register(Listener<NoticeEvent> listener, Order order, Plugin plugin) {
		handlers.register(listener, order, plugin);
	}

	@Override
	public HandlerList<NoticeEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Action Event";
	}

}