package com.alta189.chavabot.events;

/**
 * @author lahwran
 * @param <TEvent> Event type
 */
public interface Listener<TEvent extends Event<TEvent>> {

	/**
	 * Handle an event
	 * 
	 * @param event Event to handle
	 */
	public void onEvent(TEvent event);
}

