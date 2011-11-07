package com.alta189.chavabot.events;

import com.alta189.chavabot.plugins.Plugin;

/**
 * @author lahwran
 * @param <TEvent> Event class
 */
public class ListenerRegistration<TEvent extends Event<TEvent>> {
	private final Listener<TEvent> listener;
	private final Order orderslot;
	private final Plugin plugin;

	/**
	 * 
	 * @param listener Listener this registration represents
	 * @param orderslot Order position this registration is in
	 * @param plugin plugin that created this registration
	 */
	public ListenerRegistration(final Listener<TEvent> listener, final Order orderslot, final Plugin plugin) {
		this.listener = listener;
		this.orderslot = orderslot;
		this.plugin = plugin;
	}

	/**
	 * Gets the listener for this registration
	 * 
	 * @return Registered Listener
	 */
	public Listener<TEvent> getListener() {
		return listener;
	}

	/**
	 * Gets the Plugin for this registration
	 * 
	 * @return Registered Plugin
	 */
	public Plugin getPlugin() {
		return plugin;
	}

	/**
	 * Gets the order slot for this registration
	 * 
	 * @return Registered order
	 */
	public Order getOrder() {
		return orderslot;
	}
}
