package com.alta189.chavabot.botevents;

import com.alta189.chavabot.events.HandlerList;

public class SendNoticeEvent extends BotEvent<SendNoticeEvent> {
	private static final SendNoticeEvent instance = new SendNoticeEvent();
	private static final HandlerList<SendNoticeEvent> handlers = new HandlerList<SendNoticeEvent>();
	private String notice;
	private String target;

	public static SendNoticeEvent getInstance(String notice, String target) {
		instance.notice = notice;
		instance.target = target;
		return instance;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMessage() {
		return notice;
	}

	public String getTarget() {
		return target;
	}

	@Override
	public HandlerList<SendNoticeEvent> getHandlers() {
		return handlers;
	}

	@Override
	protected String getEventName() {
		return "Send Notice Event";
	}

	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}

}
