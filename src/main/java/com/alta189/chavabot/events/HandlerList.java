package com.alta189.chavabot.events;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map.Entry;

import com.alta189.chavabot.plugins.Plugin;
import com.alta189.chavabot.plugins.IllegalPluginAccessException;

/**
 * @author lahwran
 * @param <TEvent> Event type
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class HandlerList<TEvent extends Event<TEvent>> {
	/**
	 * handler array. this field being an array is the key to this system's speed.
	 * 
	 * is initialized in bake().
	 */
	public Listener<TEvent>[][] handlers;

	/**
	 * Int array same length as handlers. each value in this array is the index of an Order slot, corossponding to the equivalent value in handlers.
	 * 
	 * is initialized in bake().
	 */
	public int[] handlerids;

	/**
	 * Dynamic handler lists. These are changed using register() and unregister() and are automatically baked to the handlers array any time they have changed.
	 */
	private final EnumMap<Order, ArrayList<ListenerRegistration<TEvent>>> handlerslots;

	/**
	 * Whether the current handlerslist has been fully baked. When this is set to false, the Map<Order, List<Listener>> will be baked to Listener[][] next time the event is called.
	 * 
	 * @see EventManager.callEvent
	 */
	private boolean baked = false;

	/**
	 * List of all handlerlists which have been created, for use in bakeall()
	 */
	private static ArrayList<HandlerList> alllists = new ArrayList<HandlerList>();

	/**
	 * Bake all handler lists. Best used just after all normal event registration is complete, ie just after all plugins are loaded if you're using fevents in a plugin system.
	 */
	public static void bakeall() {
		for (HandlerList h : alllists) {
			h.bake();
		}
	}

	public static void purgePlugin(Plugin plugin) {
		for (HandlerList h : alllists) {
			h.unregister(plugin);
		}
	}

	public static void clearAll() {
		for (HandlerList h : alllists) {
			h.clear();
		}
	}

	/**
	 * Create a new handler list and initialize using EventManager.Order handlerlist is then added to meta-list for use in bakeall()
	 */
	public HandlerList() {
		handlerslots = new EnumMap<Order, ArrayList<ListenerRegistration<TEvent>>>(Order.class);
		for (Order o : Order.values()) {
			handlerslots.put(o, new ArrayList<ListenerRegistration<TEvent>>());
		}
		alllists.add(this);
	}

	private boolean isRegistered(ListenerRegistration registration, Order orderslot) {
		for (ListenerRegistration other : handlerslots.get(orderslot)) {
			if (other.getListener() == registration.getListener() && other.getPlugin() == registration.getPlugin()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Register a new listener in this handler list
	 * 
	 * @param listener listener to register
	 * @param order order location at which to call provided listener
	 * @param plugin Plugin this listener belongs to
	 */
	public void register(Listener<TEvent> listener, Order order, Plugin plugin) {
		if (!plugin.isEnabled()) {
			throw new IllegalPluginAccessException("Plugin attempted to register a listener while not enabled");
		}
		ListenerRegistration registration = new ListenerRegistration(listener, order, plugin);
		if (isRegistered(registration, order)) {
			throw new IllegalStateException("This listener is already registered to order " + order.toString());
		}

		handlerslots.get(order).add(registration);
		baked = false;
	}

	/**
	 * Remove a listener from all order slots
	 * 
	 * @param listener listener to purge
	 */
	public void unregister(Listener<TEvent> listener) {
		for (Order o : Order.values()) {
			unregister(listener, o);
		}
	}

	/**
	 * Remove a listener from a specific order slot
	 * 
	 * @param listener listener to remove
	 * @param order order from which to remove listener
	 */
	public void unregister(Listener<TEvent> listener, Order order) {
		for (ListenerRegistration registration : handlerslots.get(order)) {
			if (registration.getListener() == listener) {
				baked = false;
				handlerslots.get(order).remove(registration);
			}
		}
	}

	/**
	 * Remove a plugin from all order slots
	 * 
	 * @param plugin plugin to remove
	 */
	public void unregister(Plugin plugin) {
		for (Order o : Order.values()) {
			unregister(plugin, o);
		}
	}

	/**
	 * Remove a plugin from a specific order slot
	 * 
	 * @param plugin plugin to remove
	 * @param order order from which to remove plugin
	 */
	public void unregister(Plugin plugin, Order order) {
		for (ListenerRegistration registration : handlerslots.get(order)) {
			if (registration.getPlugin() == plugin) {
				baked = false;
				handlerslots.get(order).remove(registration);
			}
		}
	}

	private void clear() {
		for (Entry<Order, ArrayList<ListenerRegistration<TEvent>>> entry : handlerslots.entrySet()) {
			entry.getValue().clear();
		}
		baked = false;
	}

	/**
	 * Bake HashMap and ArrayLists to 2d array - does nothing if not necessary
	 */
	public void bake() {
		if (baked)
			return; // don't re-bake when still valid

		ArrayList<Listener[]> handlerslist = new ArrayList<Listener[]>();
		ArrayList<Integer> handleridslist = new ArrayList<Integer>();
		for (Entry<Order, ArrayList<ListenerRegistration<TEvent>>> entry : handlerslots.entrySet()) {
			Order orderslot = entry.getKey();

			ArrayList<ListenerRegistration<TEvent>> list = entry.getValue();

			int ord = orderslot.getIndex();
			Listener[] array = new Listener[list.size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = list.get(i).getListener();
			}
			handlerslist.add(array);
			handleridslist.add(ord);
		}
		handlers = handlerslist.toArray(new Listener[handlerslist.size()][]);
		handlerids = new int[handleridslist.size()];
		for (int i = 0; i < handleridslist.size(); i++) {
			handlerids[i] = handleridslist.get(i);
		}
		baked = true;
	}
}
